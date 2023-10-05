package com.homework.data;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Contact implements Serializable {
//    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, message = "姓名长度至少为1")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "姓名长度至少为1")
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "邮箱不为空")
    @Email(message = "请输入有效邮箱")
    private String emailAddress;


}