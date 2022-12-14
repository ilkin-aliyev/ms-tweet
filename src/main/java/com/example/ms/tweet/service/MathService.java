package com.example.ms.tweet.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MathService {
    public BigDecimal sum(BigDecimal firstElement, BigDecimal secondElement) {
        if (firstElement == null || secondElement == null) {
            throw new RuntimeException("Elements can't be null");
        }
        return firstElement.add(secondElement);
    }
}
