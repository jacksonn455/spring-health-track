package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.domains.User;
import com.springhealthtrack.api.dtos.DataAutenticationDTO;
import com.springhealthtrack.api.dtos.DataAutenticationJwtDTO;
import com.springhealthtrack.api.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager manager;

    @Mock
    private TokenService tokenService;

    @Mock
    private Authentication authentication;

    @Mock
    private User user;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnJwtToken() {
        DataAutenticationDTO request = new DataAutenticationDTO("john.doe", "123456");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.login(), request.password());

        when(manager.authenticate(authToken)).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.createToken(user)).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = authenticationController.login(request);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isInstanceOf(DataAutenticationJwtDTO.class);

        DataAutenticationJwtDTO jwtResponse = (DataAutenticationJwtDTO) response.getBody();
        assertThat(jwtResponse.token()).isEqualTo("fake-jwt-token");

        verify(manager, times(1)).authenticate(authToken);
        verify(tokenService, times(1)).createToken(user);
    }
}
