package com.ludogoriesoft.productservice.service;

import com.ludogoriesoft.productservice.dto.ProductRequest;
import com.ludogoriesoft.productservice.dto.ProductResponse;
import com.ludogoriesoft.productservice.model.Product;
import com.ludogoriesoft.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public void createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }
}
