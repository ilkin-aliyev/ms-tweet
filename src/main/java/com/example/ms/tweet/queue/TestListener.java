package com.example.ms.tweet.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestListener {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.test}")
    public void receiveMessage(String message) {
        log.info("ActionLog.receiveMessage.start message: {}", message);

        try {

        } catch (Exception ex) {
            log.error("ActionLog.receiveMessage.error: ", ex);
        }
    }
}
