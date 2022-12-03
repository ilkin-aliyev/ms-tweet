package com.example.ms.tweet.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("supplierService")
public class SupplierOrderService implements OrderService {
    @Override
    public String getOrderResult() {
        return "I'm give the money";
    }
}
