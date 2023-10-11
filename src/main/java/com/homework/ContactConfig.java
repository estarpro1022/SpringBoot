package com.homework;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.homework", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Aspect.class))
public class ContactConfig {
}
