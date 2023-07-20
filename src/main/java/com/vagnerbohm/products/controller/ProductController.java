package com.vagnerbohm.products.controller;

import com.vagnerbohm.products.dto.ProductRequestDTO;
import com.vagnerbohm.products.dto.ProductResponseDTO;
import com.vagnerbohm.products.model.ProductModel;
import com.vagnerbohm.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable UUID id) {
        return this.productRepository.findById(id)
                .map(ProductResponseDTO::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductRequestDTO product) {
        ProductModel productModel = new ProductModel(product);
        this.productRepository.save(productModel);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        if(!this.productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }

        this.productRepository.deleteById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody ProductRequestDTO product) {
        Optional<ProductModel> productGet = this.productRepository.findById(id);

        if (productGet.isPresent()) {
            if(product.name() != null) {
                productGet.get().setName(product.name());
            }
            productGet.get().setType(product.type());
            productGet.get().setPrice(product.price());

            this.productRepository.save(productGet.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }

    }
}


