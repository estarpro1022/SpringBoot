package com.homework;

import com.homework.data.DefaultContactAspect;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ContactConfig.class)})
@EnableAspectJAutoProxy
public class ContactConfigAOP {
    @Bean
    public DefaultContactAspect getDefaultContact() {
        return new DefaultContactAspect();
    }
}
