package com.bfz.completablefuture.infrastructure.adapter.out.externalservice;

import com.bfz.completablefuture.application.port.out.externalservice.ProductService;
import com.bfz.completablefuture.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product findById(Long id) {
        log.info("Call to product service... ");
        try {
            Thread.sleep(1500); // Simulating a network delay
            return new Product(1L, "Demo Product", 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
