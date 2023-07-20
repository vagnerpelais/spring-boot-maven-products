package com.vagnerbohm.products.dto;

import com.vagnerbohm.products.model.ProductModel;
import com.vagnerbohm.products.model.enums.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, Double price, Type type, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public ProductResponseDTO(ProductModel product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getType(), product.getCreatedAt(), product.getUpdatedAt());
    }
}
