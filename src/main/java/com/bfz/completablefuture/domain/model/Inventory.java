package com.bfz.completablefuture.domain.model;

import java.util.Objects;

public record Inventory(
        Long id,
        Integer availableQuantity
) {

    public boolean hasStockAvailable(Integer quantity) {
        return Objects.nonNull(availableQuantity) && (availableQuantity - quantity) > 0;
    }

}
