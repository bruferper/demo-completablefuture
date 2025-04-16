package com.bfz.completablefuture.infrastructure.adapter.out.repository;

import com.bfz.completablefuture.application.port.out.repository.OrderRepository;
import com.bfz.completablefuture.domain.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public void create(Order order) {
        log.info("Saving offer with ID {} and totalPrice {} in the database...", order.id(), order.totalPrice());
    }

}
