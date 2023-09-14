package com.xllWhiteReaper.security_v2.controllers;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xllWhiteReaper.security_v2.repositories.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.xllWhiteReaper.security_v2.models.Product;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products != null ? ResponseEntity.ok(products) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception, HttpServletRequest request) {
        Map<String, String> apiErrorMap = new HashMap<String, String>();
        apiErrorMap.put("error", exception.getLocalizedMessage());
        apiErrorMap.put("timestamp", new Date().toString());
        apiErrorMap.put("url", request.getRequestURL().toString());
        apiErrorMap.put("http-method", request.getMethod());

        HttpStatus status = (exception instanceof AccessDeniedException) ? HttpStatus.FORBIDDEN
                : (exception instanceof NotFoundException) ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status.value()).body(apiErrorMap);
    }
}
