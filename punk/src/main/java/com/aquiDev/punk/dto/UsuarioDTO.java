package com.aquiDev.punk.dto;

import com.aquiDev.punk.entidades.Usuario;

public class UsuarioDTO {

    private String username;

    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO createDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setpassword(usuario.getPassword());
        return usuarioDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
