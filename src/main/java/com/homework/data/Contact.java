package com.homework.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Contact(String fn, String ln, String pn, String ea) {
        firstName = fn;
        lastName = ln;
        phoneNumber = pn;
        emailAddress = ea;
    }

}