package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.domains.User;
import com.springhealthtrack.api.dtos.DataAutenticationDTO;
import com.springhealthtrack.api.dtos.DataAutenticationJwtDTO;
import com.springhealthtrack.api.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAutenticationDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(token);
        var jwtToken = tokenService.createToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new DataAutenticationJwtDTO(jwtToken));
    }
}
