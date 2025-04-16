package com.bfz.completablefuture.application.port.out.repository;

import com.bfz.completablefuture.domain.model.Order;

public interface OrderRepository {

    void create(Order order);

}
