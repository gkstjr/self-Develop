package com.selfdev.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        //userId(이름만)
        SignUpForm signUpForm = (SignUpForm) object;
        if(accountRepository.existsByUserId(signUpForm.getUserId())) {
            errors.rejectValue("userId","invalid.userId", new Object[]{signUpForm.getUserId()},"이미 사용중인 아이디입니다.");
        }
    }
}
