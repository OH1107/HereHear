//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.ssafy.herehear.api.service.AccountService;
//import com.ssafy.herehear.common.util.JwtTokenUtil;
//import com.ssafy.herehear.db.entity.Account;
//
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//	private AccountService accountService;
//
//	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AccountService accountService) {
//		super(authenticationManager);
//		this.accountService = accountService;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// Read the Authorization header, where the JWT Token should be
//		String header = request.getHeader(JwtTokenUtil.HEADER_STRING);
//		
//		// If header does not contain BEARER or is null delegate to Spring impl and exit
//		if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		try {
//			// If header is present, try grab user principal from database and perform
//			// authorization
//			Authentication authentication = getAuthentication(request);
//			// jwt ��ū���� ���� ȹ���� ���� ����(authentication) ����.
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		} catch (Exception ex) {
//			ResponseBodyWriteUtil.sendError(request, response, ex);
//			return;
//		}
//
//		filterChain.doFilter(request, response);
//	}
//
//	@Transactional(readOnly = true)
//	public Authentication getAuthentication(HttpServletRequest request) throws Exception {
//		String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
//		// ��û ����� Authorization Ű���� jwt ��ū�� ���Ե� ��쿡��, ��ū ���� �� ���� ó�� ���� ����.
//		if (token != null) {
//			JWTVerifier verifier = JwtTokenUtil.getVerifier();
//			JwtTokenUtil.handleError(token);
//			DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
//			String userId = decodedJWT.getSubject();
//
//			if (userId != null) {
//				// jwt ��ū�� ���Ե� ���� ����(userId) ���� ���� ��� �ش� ������ ������ �ִ��� ��ȸ.
//				Account account = accountService.getAccount(username);
//				if (user != null) {
//					// �ĺ��� ���� ������ ���, ��û context ������ ���� ������ ���� ����(jwtAuthentication) ����.
//					SsafyUserDetails userDetails = new SsafyUserDetails(user);
//					UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(
//							userId, null, userDetails.getAuthorities());
//					jwtAuthentication.setDetails(userDetails);
//					return jwtAuthentication;
//				}
//			}
//			return null;
//		}
//		return null;
//	}
//}


/**
 * ���� ���� �ذ�
 * ���� ��Ű�� �������� ���Ѵٰ� ������, localStorage���� �ҷ����� ������ �� �ؾ���
 */
package com.ssafy.herehear.common.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.herehear.common.util.CookieUtil;
import com.ssafy.herehear.common.util.JwtTokenUtil;
import com.ssafy.herehear.common.util.RedisUtil;
import com.ssafy.herehear.db.entity.Account;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;  
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final Cookie jwtToken = cookieUtil.getCookie(httpServletRequest,jwtTokenUtil.ACCESS_TOKEN_NAME);

        String username = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try{
            if(jwtToken != null){
                jwt = jwtToken.getValue();
                username = jwtTokenUtil.getUsername(jwt);
            }
            if(username!=null){
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

                if(jwtTokenUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException e){
            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest, jwtTokenUtil.REFRESH_TOKEN_NAME);
            if(refreshToken!=null){
                refreshJwt = refreshToken.getValue();
            }
        }catch(Exception e){

        }

        try{
            if(refreshJwt != null){
                refreshUname = redisUtil.getData(refreshJwt);
                if(refreshUname.equals(jwtTokenUtil.getUsername(refreshJwt))){
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    Account account = new Account();
                    account.setUsername(refreshUname);
                    String newToken =jwtTokenUtil.generateToken(account);

                    Cookie newAccessToken = cookieUtil.createCookie(jwtTokenUtil.ACCESS_TOKEN_NAME, newToken);
                    httpServletResponse.addCookie(newAccessToken);
                    }
            }
        }catch(ExpiredJwtException e){

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}