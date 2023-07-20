package com.vagnerbohm.products.repository;

import com.vagnerbohm.products.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
    void deleteById(UUID id);

}
