package com.vagnerbohm.products.controller;

import com.vagnerbohm.products.dto.ProductRequestDTO;
import com.vagnerbohm.products.dto.ProductResponseDTO;
import com.vagnerbohm.products.model.ProductModel;
import com.vagnerbohm.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAll(){
        return this.productRepository.findAll().stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductRequestDTO product) {
        ProductModel productModel = new ProductModel(product);
        this.productRepository.save(productModel);
    }
}
