package com.bfz.completablefuture.infrastructure.adapter.out.externalservice;

import com.bfz.completablefuture.application.port.out.externalservice.InventoryService;
import com.bfz.completablefuture.domain.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public Inventory findByProductId(Long id) {
        try {
            Thread.sleep(1000); // Simulating a network delay
            return new Inventory(1L, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
