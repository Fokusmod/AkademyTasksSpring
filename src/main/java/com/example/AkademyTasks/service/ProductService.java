package com.example.AkademyTasks.service;

import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.Product;
import com.example.AkademyTasks.repository.ProductRepository;
import com.example.AkademyTasks.utils.JsonMapper;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public String getCurrentProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукта с указанным id не удалось найти."));
        return JsonMapper.toJson(product);
    }

    public ResponseEntity<String> createNewProduct(String json) {
        Product product = JsonMapper.toObject(json, Product.class);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Успешное создание продукта");
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Успешное удаление продукта");
    }

    public ResponseEntity<String> updateProduct(String json) {
        Product product = JsonMapper.toObject(json, Product.class);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body("Успешное обновление информации о продукте");
    }

    public List<String> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(JsonMapper::toJson).toList();
    }
}
