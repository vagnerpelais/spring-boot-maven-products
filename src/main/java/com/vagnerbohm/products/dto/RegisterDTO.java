package com.vagnerbohm.products.dto;

import com.vagnerbohm.products.model.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
