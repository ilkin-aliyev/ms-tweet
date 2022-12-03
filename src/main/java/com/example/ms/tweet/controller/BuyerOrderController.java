package com.example.ms.tweet.controller;

import com.example.ms.tweet.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tweet/orders")
public class BuyerOrderController {
    private final OrderService orderService;

    public BuyerOrderController(@Qualifier("supplierService") OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/results")
    public String getOrderResults() {
        return orderService.getOrderResult();
    }
}
