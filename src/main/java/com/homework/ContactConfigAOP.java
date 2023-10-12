package com.homework;

import com.homework.data.DefaultContactAspect;
import org.springframework.context.annotation.*;

@ComponentScan
@EnableAspectJAutoProxy
public class ContactConfigAOP {
    @Bean
    public DefaultContactAspect getDefaultContact() {
        return new DefaultContactAspect();
    }
}
