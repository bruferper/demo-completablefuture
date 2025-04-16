package com.bfz.completablefuture.infrastructure.adapter.in.rest.dto;

public record CreateOrderCommand(
        Long productId,
        Integer quantity
) { }
