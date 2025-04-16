package com.bfz.completablefuture.application.port.out.externalservice;

import com.bfz.completablefuture.domain.model.Inventory;

public interface InventoryService {

    Inventory findByProductId(Long id);

}
