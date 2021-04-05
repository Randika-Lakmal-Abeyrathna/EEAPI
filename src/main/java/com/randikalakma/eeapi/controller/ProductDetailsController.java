package com.randikalakma.eeapi.controller;

import com.randikalakma.eeapi.model.ProductDetails;
import com.randikalakma.eeapi.service.ProductDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/product/")
@AllArgsConstructor
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDetails>> getAllProducts(){
        List<ProductDetails> productDetailsList= productDetailsService.getAllProducts();
        return new ResponseEntity<>(productDetailsList, HttpStatus.OK);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<ProductDetails> getProductById(@PathVariable("id") Integer id){
        ProductDetails productDetails = productDetailsService.getProductById(id);
        return new ResponseEntity<>(productDetails,HttpStatus.OK);
    }
}
