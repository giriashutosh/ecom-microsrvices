package com.ecomm.product.service;

import com.ecomm.product.Entity.Product;
import com.ecomm.product.dto.ProductRequest;
import com.ecomm.product.dto.ProductResponse;
import com.ecomm.product.repositiry.ProductRepositiry;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepositiry productRepositiry;

    public ProductServiceImpl(ProductRepositiry productRepositiry){
        this.productRepositiry = productRepositiry;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product = mapProductRequestToProduct(product, productRequest);
        productRepositiry.save(product);
        ProductResponse productResponse = mapProductToProductResponse(product);
        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepositiry.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with this id:" + id));

        product = mapProductRequestToProduct(product,productRequest);
        productRepositiry.save(product);
        ProductResponse productResponse = mapProductToProductResponse(product);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllproducts() {
        return productRepositiry.findByActiveTrue()
                .stream()
                .map(this::mapProductToProductResponse)
                .collect(Collectors.toList());

    }

    @Override
    public Boolean deleteProduct(Long id) {
        return productRepositiry.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepositiry.save(product);
                    return true;
                }).orElse(false);

    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepositiry.searchProducts(keyword)
                .stream()
                .map(this::mapProductToProductResponse)
                .collect(Collectors.toList());

    }

    public Product mapProductRequestToProduct(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        return product;
    }

    public ProductResponse mapProductToProductResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setDescription(product.getDescription());
        response.setImageUrl(product.getImageUrl());
        return response;
    }

}
