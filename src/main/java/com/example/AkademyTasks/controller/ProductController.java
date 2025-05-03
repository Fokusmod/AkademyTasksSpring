package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.model.Product;
import com.example.AkademyTasks.service.ProductService;
import com.example.AkademyTasks.utils.JsonMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all_products")
    public List<String> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public String getCurrentProduct(@PathVariable Long id) {
        return productService.getCurrentProduct(id);
    }

    @PostMapping("/product")
    public ResponseEntity<String> createNewProduct(@RequestBody @NotBlank String json) {
       return productService.createNewProduct(json);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/product")
    public ResponseEntity<String> updateProduct(@RequestBody @NotBlank String json) {
        return productService.updateProduct(json);
    }
}
