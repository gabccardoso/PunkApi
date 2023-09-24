package com.aquiDev.punk.dto;

import com.aquiDev.punk.entidades.Usuario;

public class UsuarioDTO {

    private String username;

    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO createDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setSenha(usuario.getSenha());
        return usuarioDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
