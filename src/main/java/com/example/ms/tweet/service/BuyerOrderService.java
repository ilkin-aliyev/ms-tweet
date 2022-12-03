package com.example.ms.tweet.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("buyerService")
public class BuyerOrderService implements OrderService{
    @Override
    public String getOrderResult() {
        return "I'm spent money";
    }
}
