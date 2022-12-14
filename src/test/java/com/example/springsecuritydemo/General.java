package com.example.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ROLE_GENERALでテスト実行
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl",
        value = "general@example.com",
        setupBefore = TestExecutionEvent.TEST_EXECUTION)
public @interface General {
}