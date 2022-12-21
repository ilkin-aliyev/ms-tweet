package com.example.ms.tweet.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String testQ;
    private final String testDLQ;
    private final String testQExchange;
    private final String testDLQExchange;
    private final String testQKey;
    private final String testDLQKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.test}") String testQ,
                          @Value("${rabbitmq.queue.test-dlq}") String testDLQ) {
        this.testQ = testQ;
        this.testDLQ = testDLQ;
        this.testQExchange = testQ + "_Exchange";
        this.testDLQExchange = testDLQ + "_Exchange";
        this.testQKey = testQ + "_Key";
        this.testDLQKey = testDLQ + "_Key";
    }

    @Bean
    DirectExchange testDLQExchange() {
        return new DirectExchange(testDLQExchange);
    }

    @Bean
    DirectExchange testQExchange() {
        return new DirectExchange(testQExchange);
    }

    @Bean
    Queue testDLQ() {
        return QueueBuilder.durable(testDLQ).build();
    }

    @Bean
    Queue testQ() {
        return QueueBuilder.durable(testQ)
                .withArgument("x-dead-letter-exchange", testDLQExchange)
                .withArgument("x-dead-letter-routing-key", testDLQKey)
                .build();
    }

    @Bean
    Binding testDLQBinding() {
        return BindingBuilder.bind(testDLQ())
                .to(testDLQExchange()).with(testDLQKey);
    }

    @Bean
    Binding testQBinding() {
        return BindingBuilder.bind(testQ())
                .to(testQExchange()).with(testQKey);
    }
}  
