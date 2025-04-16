package com.bfz.completablefuture.application.port.out.externalservice;

import com.bfz.completablefuture.domain.model.Product;

public interface ProductService {

    Product findById(Long id);

}
