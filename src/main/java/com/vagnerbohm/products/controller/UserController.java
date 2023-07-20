package com.vagnerbohm.products.controller;

import com.vagnerbohm.products.dto.UserResponseDTO;
import com.vagnerbohm.products.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<UserResponseDTO> getAll() {
        return this.userRepository.findAll().stream().map(UserResponseDTO::fromUserModel).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable UUID id) {
        return UserResponseDTO.fromUserModel(this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.userRepository.deleteById(id);
    }

}
