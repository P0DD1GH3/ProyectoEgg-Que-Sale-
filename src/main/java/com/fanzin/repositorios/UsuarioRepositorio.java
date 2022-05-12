package com.fanzin.repositorios;

import com.fanzin.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    /*
    @Query("SELECT u FROM Usuario u WHERE u.id LIKE :id")
    public Usuario buscarPorId(@Param("id")String id);
    */

}
