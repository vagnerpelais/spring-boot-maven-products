package com.vagnerbohm.products.dto;

import com.vagnerbohm.products.model.enums.Type;

public record ProductRequestDTO(String name, Double price, Type type) {

}
