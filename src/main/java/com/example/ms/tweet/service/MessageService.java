package com.example.ms.tweet.service;

import com.example.ms.tweet.model.dto.MessageDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class MessageService {

    @SneakyThrows
    public MessageDto getMessage(String lang) {
        Thread.sleep(new Random().nextInt(1000));
        return new MessageDto("Hello Ingress");
    }

    @SneakyThrows
    public MessageDto getMessage2() {
        Thread.sleep(new Random().nextInt(1000));
        return new MessageDto("Hello Ingress");
    }
}
