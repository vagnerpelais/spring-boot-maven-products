package com.vagnerbohm.products.model;

import com.vagnerbohm.products.dto.ProductRequestDTO;
import com.vagnerbohm.products.model.enums.Type;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "products")
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column()
    private Double price;

    @Enumerated(EnumType.STRING)
    private Type type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ProductModel(ProductRequestDTO product) {
        this.name = product.name();
        this.price = product.price();
        this.type = product.type();
    }

    public ProductModel() {

    }
}
