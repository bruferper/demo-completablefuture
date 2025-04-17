package com.bfz.completablefuture.infrastructure.adapter.out.externalservice;

import com.bfz.completablefuture.application.port.out.externalservice.InventoryService;
import com.bfz.completablefuture.domain.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public Inventory findByProductId(Long id) {
        log.info("Call to inventory service... ");
        try {
            Thread.sleep(1000); // Simulating a network delay
            if(id == 10) throw new RuntimeException("Error getting inventory for Product ID " + id);
            return new Inventory(1L, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
