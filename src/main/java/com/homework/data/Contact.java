package com.homework.data;

//import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Document
public class Contact implements Serializable {
    @Id
    private String id;

    @NotNull
    @Size(min = 1, max=50, message = "姓名长度至少为2")
    private String firstName;

    @NotNull
    @Size(min = 1, max=50, message = "姓名长度至少为1")
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "邮箱不为空")
    @Email(message = "请输入有效邮箱")
    private String emailAddress;
}