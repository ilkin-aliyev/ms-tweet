package com.example.ms.tweet.controller;

import com.example.ms.tweet.model.dto.MessageDto;
import com.example.ms.tweet.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tweet/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public MessageDto getMessage() {
        return messageService.getMessage("English");
    }

    @GetMapping("/2")
    public MessageDto getMessage2() {
        return messageService.getMessage2();
    }
}
