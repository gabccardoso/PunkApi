package com.aquiDev.punk.controller;

import com.aquiDev.punk.dto.UsuarioDTO;
import com.aquiDev.punk.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/cria")
    public ResponseEntity<String> cria(@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO.setpassword(passwordEncoder.encode(usuarioDTO.getpassword()));
        String usuarioUsername = usuarioService.criaNovoUsuario(usuarioDTO);
        return ResponseEntity.ok("teste");
    }

}
