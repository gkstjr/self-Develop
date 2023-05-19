package com.selfdev.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$",message = "아이디를 공백없이 문자와 숫자로만 3자 이상 20자 이내로 입력해주세요")
    @NotBlank(message ="")
    @Size(min = 3, max =20,message = "")
    private String userId;

    @Size(min = 4, max =50 , message = "비밀번호는 4자 이상 50자 이내로 입력해주세요.")
    @NotBlank(message = "")
    private String password;

    @Pattern(regexp = "^[가-힣]{2,4}$",message = "이름은 한글 2~4자 사이로 입력해주세요.")
    @NotBlank(message = "")
    @Size(min = 2, max =4,message = "")
    private String name;

}
