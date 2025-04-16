package com.bfz.completablefuture.application.port.in;

public interface CreateOrderUseCase {

    void createOrder(Long productId, Integer quantity);

}
