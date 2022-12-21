package com.example.ms.tweet.util;

import org.springframework.stereotype.Component;

@Component
public class PersonUtil {

    public boolean checkAge(Integer age) {
        return age >= 18;
    }
}
