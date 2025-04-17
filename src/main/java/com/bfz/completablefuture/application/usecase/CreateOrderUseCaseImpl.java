package com.bfz.completablefuture.application.usecase;

import com.bfz.completablefuture.application.port.in.CreateOrderUseCase;
import com.bfz.completablefuture.application.port.out.externalservice.InventoryService;
import com.bfz.completablefuture.application.port.out.externalservice.ProductService;
import com.bfz.completablefuture.application.port.out.repository.OrderRepository;
import com.bfz.completablefuture.domain.exception.InsufficientStockException;
import com.bfz.completablefuture.domain.model.Inventory;
import com.bfz.completablefuture.domain.model.Order;
import com.bfz.completablefuture.domain.model.Product;
import com.bfz.completablefuture.domain.model.ProductDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


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
        ProductDetail productDetail = findProductDetail(productId);
        if(!productDetail.inventory().hasStockAvailable(quantity)) throw new InsufficientStockException("No stock available");
        Integer totalPrice = quantity * productDetail.product().price();
        Order order = new Order(1L, totalPrice);
        orderRepository.create(order);
        log.info("Order with ID {} has been created", order.id());
    }

    private ProductDetail findProductDetail(Long productId) {
        CompletableFuture<Inventory> inventoryFuture = CompletableFuture
                .supplyAsync(() -> inventoryService.findByProductId(productId))
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return new Inventory(0L, 0);
                });
        CompletableFuture<Product> productFuture = CompletableFuture
                .supplyAsync(() -> productService.findById(productId))
                .exceptionally(ex -> {
                  log.error(ex.getMessage());
                  return new Product(0L, "", 0);
                });
        return inventoryFuture
                .thenCombine(productFuture, (inventory, product) -> new ProductDetail(product, inventory))
                .join();
    }

}
