package com.ssafy.herehear.api.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.herehear.api.request.AccountReq;
import com.ssafy.herehear.api.response.AccountRes;
import com.ssafy.herehear.common.jwt.TokenDto;
import com.ssafy.herehear.common.jwt.TokenProvider;
import com.ssafy.herehear.common.jwt.TokenReqDto;
import com.ssafy.herehear.common.util.RedisUtil;
import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    
    @Transactional
    public AccountRes signup(AccountReq accountReq) {
        if (accountRepository.existsByUsername(accountReq.getUsername())) {
            throw new RuntimeException("�̹� ���ԵǾ� �ִ� �����Դϴ�");
        }

        Account account = accountReq.toAccount(passwordEncoder);
        return AccountRes.of(accountRepository.save(account));
    }
    
    @Transactional
    public TokenDto login(AccountReq accountReq) {
        // 1. Login ID/PW �� ������� AuthenticationToken ����
        UsernamePasswordAuthenticationToken authenticationToken = accountReq.toAuthentication();

        // 2. ������ ���� (����� ��й�ȣ üũ) �� �̷������ �κ�
        //    authenticate �޼��尡 ������ �� �� CustomUserDetailsService ���� ������� loadUserByUsername �޼��尡 �����
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. ���� ������ ������� JWT ��ū ����
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken ����
        redisUtil.setDataExpire(authentication.getName(), tokenDto.getRefreshToken(), 1000 * 60 * 60 * 24 * 7);

        // 5. ��ū �߱�
        return tokenDto;
    }
    
    @Transactional
    public TokenDto reissue(TokenReqDto toeknReqDto) {
        // 1. Refresh Token ����
        if (!tokenProvider.validateToken(toeknReqDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token �� ��ȿ���� �ʽ��ϴ�.");
        }

        // 2. Access Token ���� username ��������
        Authentication authentication = tokenProvider.getAuthentication(toeknReqDto.getAccessToken());

        // 3. redis ����ҿ��� username �� ������� Refresh Token �� ������
        String refreshToken = redisUtil.getData(authentication.getName())
        		.orElseThrow(() -> new RuntimeException("�α׾ƿ� �� ������Դϴ�."));

        // 4. Refresh Token ��ġ�ϴ��� �˻�
        if (!refreshToken.equals(toeknReqDto.getRefreshToken())) {
            throw new RuntimeException("��ū�� ���� ������ ��ġ���� �ʽ��ϴ�.");
        }

        // 5. ���ο� ��ū ����
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. ����� ���� ������Ʈ
        redisUtil.setDataExpire(authentication.getName(), tokenDto.getRefreshToken(), 1000 * 60 * 60 * 24 * 7);

        // ��ū �߱�
        return tokenDto;
    }
}
