package com.ssafy.herehear.common.auth;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> �����ͺ��̽����� ã�� �� �����ϴ�."));
    }

    // DB �� User ���� �����Ѵٸ� UserDetails ��ü�� ���� ����
    private UserDetails createUserDetails(Account account) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getAuthority().toString());

        return new User(
                String.valueOf(account.getId()),
                account.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}