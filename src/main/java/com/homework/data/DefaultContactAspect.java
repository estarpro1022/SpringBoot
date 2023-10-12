package com.homework.data;


import com.homework.utils.GetContact;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

@Aspect
public class DefaultContactAspect {
    @Pointcut("execution(* com.homework.service.ContactService.getAll( .. ))")
    public void getAll() {}

    @Around("getAll()")
    public List<Contact> returnContact(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            proceedingJoinPoint.proceed();
            return GetContact.expectedContacts();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
