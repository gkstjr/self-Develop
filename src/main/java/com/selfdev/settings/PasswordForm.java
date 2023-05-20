package com.selfdev.settings;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordForm {

    @Length(min = 4, max = 50)
    private String newPassword;

    @Length(min = 4 , max = 50)
    private String newPasswordConfirm;

}
