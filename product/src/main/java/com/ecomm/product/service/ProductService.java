package com.ecomm.product.service;

import com.ecomm.product.dto.ProductRequest;
import com.ecomm.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest, Long id);
    List<ProductResponse> getAllproducts();
    Boolean deleteProduct(Long id);
    List<ProductResponse> searchProducts(String keyword);
}
