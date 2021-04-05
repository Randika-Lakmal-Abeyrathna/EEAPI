package com.randikalakma.eeapi.service;

import com.randikalakma.eeapi.exception.ProductDetailsException;
import com.randikalakma.eeapi.model.ProductDetails;
import com.randikalakma.eeapi.repository.ProductDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetails> getAllProducts(){
        return productDetailsRepository.findAll();
    }

    public ProductDetails getProductById(Integer id){
        return productDetailsRepository.findProductDetailsById(id)
                .orElseThrow(()-> new ProductDetailsException("Product Details by id "+id+" not found"));
    }

    // Add product

    // product validation





}
