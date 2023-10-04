package com.homework.data;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Contact implements Serializable {
//    @NotNull
    private Long id;

    @Size(min = 1, message = "姓名长度至少为1")
    private String firstName;

    @Size(min = 1, message = "姓名长度至少为1")
    private String lastName;

    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phoneNumber;

    @NotBlank
    @Email(message = "请输入有效邮箱")
    private String emailAddress;


}