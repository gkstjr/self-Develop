package com.selfdev.account;

import com.selfdev.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account saveNewAccount(SignUpForm signUpForm) {
        Account account = Account.builder()
                .userId(signUpForm.getUserId())
                .password(passwordEncoder.encode(signUpForm.getPassword())) // encoding해야함
                .name(signUpForm.getName())
                .joinedAt(LocalDateTime.now())
                .build();
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId);
        if(account == null) {
            throw new UsernameNotFoundException(userId);
        }

        return new UserAccount(account);
    }
}

