package com.selfdev.settings;

import com.selfdev.account.AccountService;
import com.selfdev.account.CurrentUser;
import com.selfdev.account.SignUpForm;
import com.selfdev.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final AccountService accountService;
    private final PasswordFormValidator passwordFormValidator;

    @InitBinder("passwordForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordFormValidator);
    }
    @GetMapping("/settings/password")
    public String updatePasswordForm(@CurrentUser Account account , Model model) { // 여기서 account를 꼭 넣어야하나? 뺴고 테스트해보기
        model.addAttribute("account",account); //model에 객체를 넣어서 보내면 model 값은 객체명의 카멜케이스 형태로 호출 가능
        model.addAttribute("passwordForm",new PasswordForm());
        return "settings/password";
    }

    @PostMapping("/settings/password")
    public String updatePassword(@CurrentUser Account account , @Valid PasswordForm passwordForm , Errors errors ,
                                 Model model , RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            model.addAttribute(account);
            return "settings/password";
        }

        accountService.updatePassword(account,passwordForm.getNewPassword());
        attributes.addFlashAttribute("message","패스워드를 변경했습니다.");
        return "redirect:/settings/password";
    }
}
