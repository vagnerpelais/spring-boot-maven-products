package com.vagnerbohm.products.security;

import com.vagnerbohm.products.repository.UserRepository;
import com.vagnerbohm.products.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.retrieveToken(request);
        if(token != null) {
            var login = this.tokenService.validateToken(token);
            UserDetails user = this.userRepository.findByLogin(login);

            var authenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var tokenHeader = request.getHeader("Authorization");
        if(tokenHeader == null) return null;

        return tokenHeader.replace("Bearer ", "");
    }
}
