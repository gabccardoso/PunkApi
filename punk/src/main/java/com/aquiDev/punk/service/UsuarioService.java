package com.aquiDev.punk.service;

import com.aquiDev.punk.dto.UsuarioDTO;
import com.aquiDev.punk.entidades.Role;
import com.aquiDev.punk.entidades.Usuario;
import com.aquiDev.punk.projections.UserDetailsProjection;
import com.aquiDev.punk.repositories.RoleRepository;
import com.aquiDev.punk.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public String criaNovoUsuario(UsuarioDTO novoUsuarioDTO){
        Usuario usuario = new Usuario();
        Role role = roleRepository.getReferenceById(1L);
        usuario.setUsername(novoUsuarioDTO.getUsername());
        usuario.setPassword(novoUsuarioDTO.getpassword());
        usuario.addRole(role);
        usuarioRepository.save(usuario);
        return usuario.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = usuarioRepository.searchUserAndRolesByUsername(username);
        if(result == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(result.get(0).getUsername());
        usuario.setPassword(result.get(0).getPassword());
        usuario.addRole(new Role(result.get(0).getRoleId(), result.get(0).getAuthority()));
        return usuario;
    }
}
