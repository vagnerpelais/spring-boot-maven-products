package com.vagnerbohm.products.dto;

import com.vagnerbohm.products.model.UserModel;
import com.vagnerbohm.products.model.enums.UserRoles;

import java.util.Optional;
import java.util.UUID;


public record UserResponseDTO(UUID id, String login, String password, UserRoles role) {
    public static UserResponseDTO fromUserModel(UserModel userModel) {
        return new UserResponseDTO(userModel.getId(), userModel.getLogin(), userModel.getPassword(), userModel.getRole());
    }
}
