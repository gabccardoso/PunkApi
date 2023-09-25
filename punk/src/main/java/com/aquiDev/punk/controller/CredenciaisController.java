package com.aquiDev.punk.controller;


import com.aquiDev.punk.config.AuthorizationServerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/credenciais")
public class CredenciaisController {

    @Value("${security.clientId}")
    private String clientId;

    @Value("${security.clientSecret}")
    private String clientSecret;

    @Autowired
    AuthorizationServerConfig authorizationServerConfig;

    @GetMapping
    public ResponseEntity<String> recuperaCredenciais() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credenciais = new HashMap<>();
        credenciais.put("clientId", clientId);
        credenciais.put("clientSecret", clientSecret);
        String credenciaisString = objectMapper.writeValueAsString(credenciais);
        return ResponseEntity.ok(credenciaisString);
    }
}
