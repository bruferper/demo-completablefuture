package com.bfz.completablefuture.application.usecase;

import com.bfz.completablefuture.application.port.in.CreateOrderUseCase;
import com.bfz.completablefuture.application.port.out.externalservice.InventoryService;
import com.bfz.completablefuture.application.port.out.externalservice.ProductService;
import com.bfz.completablefuture.application.port.out.repository.OrderRepository;
import com.bfz.completablefuture.domain.model.Inventory;
import com.bfz.completablefuture.domain.model.Order;
import com.bfz.completablefuture.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Long productId, Integer quantity) {
        log.info("Creating order...");
        Inventory inventory = inventoryService.findByProductId(productId);
        if(!inventory.hasStockAvailable(quantity)) throw new RuntimeException("No stock available");
        Product product = productService.findById(productId);
        Integer totalPrice = quantity * product.price();
        Order order = new Order(1L, totalPrice);
        orderRepository.create(order);
        log.info("Order with ID {} has been created", order.id());
    }

}
