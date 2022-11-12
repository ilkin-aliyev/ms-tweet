package com.example.ms.tweet.aop;

import com.example.ms.tweet.model.dto.MessageDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Aspect
@Component
@Slf4j
public class ElapsedTimeLogger {

    @Pointcut(value = "execution(* com.example.ms.tweet.service.MessageService.*(..))")
    public void elapsedTimePc() {
    }

    @SneakyThrows
    @Around(value = "elapsedTimePc()")
    public void elapsedTimeLogger(ProceedingJoinPoint jp) {
        long startDate = System.currentTimeMillis();
        jp.proceed();
        long endDate = System.currentTimeMillis();
        log.info("Elapsed time:{}", endDate - startDate);
    }

    @Before(value = "elapsedTimePc()")
    public void logAllParameters(JoinPoint joinPoint) {
        Arrays.stream(
                        joinPoint.getArgs())
                .forEach(arg -> log.info("All method args:{}", arg)
                );
    }
}
