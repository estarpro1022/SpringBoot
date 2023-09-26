package com.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class ContactConfigAOP {
    @Bean
    public DefaultContact getDefaultContact() {
        return new DefaultContact();
    }
}
