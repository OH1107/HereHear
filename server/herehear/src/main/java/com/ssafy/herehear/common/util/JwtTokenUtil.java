package com.ssafy.herehear.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ssafy.herehear.db.entity.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * jwt ��ū ��ƿ ����.
 */
@Component
public class JwtTokenUtil {
    
	public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;
    
    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    
    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    // ��ū�� ��ȿ���� �˻��� ��, ��ū�� ��� payload���� ����
    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // ������ payload�κ��� username Ȯ��
    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }
    
    // ��ū ���� Ȯ��
    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
    
    // access token �߱�
    public String generateToken(Account account) {
        return doGenerateToken(account.getUsername(), TOKEN_VALIDATION_SECOND);
    }
    
    // refresh token �߱�
    public String generateRefreshToken(Account account) {
        return doGenerateToken(account.getUsername(), REFRESH_TOKEN_VALIDATION_SECOND);
    }
    
    // ��ū ����, ���̷ε忡 ��� ���� username
    public String doGenerateToken(String username, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("username", username);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}