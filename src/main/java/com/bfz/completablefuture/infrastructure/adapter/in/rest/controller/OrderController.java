package com.bfz.completablefuture.infrastructure.adapter.in.rest.controller;

import com.bfz.completablefuture.application.port.in.CreateOrderUseCase;
import com.bfz.completablefuture.infrastructure.adapter.in.rest.dto.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    ResponseEntity<Void> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        createOrderUseCase.createOrder(createOrderCommand.productId(), createOrderCommand.quantity());
        return ResponseEntity.ok().build();
    }

}
