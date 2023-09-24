package com.aquiDev.punk.repositories;

import com.aquiDev.punk.entidades.Usuario;
import com.aquiDev.punk.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value = """
				SELECT tb_usuario.username AS username, tb_usuario.senha, tb_role.id AS roleId, tb_role.authority
				FROM tb_usuario
				INNER JOIN tb_usuario_role ON tb_usuario.id = tb_usuario_role.user_id
				INNER JOIN tb_role ON tb_role.id = tb_usuario_role.role_id
				WHERE tb_usuario.username = :username
			""")
    List<UserDetailsProjection> searchUserAndRolesByUsername(String username);

}
