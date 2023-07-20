package com.vagnerbohm.products.controller;

import com.vagnerbohm.products.dto.AuthDTO;
import com.vagnerbohm.products.dto.LoginResponseDTO;
import com.vagnerbohm.products.dto.RegisterDTO;
import com.vagnerbohm.products.model.UserModel;
import com.vagnerbohm.products.repository.UserRepository;
import com.vagnerbohm.products.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = this.tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String hashedPass = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.login(), hashedPass, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
