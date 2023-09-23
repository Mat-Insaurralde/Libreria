
package com.LastByte.libreria.repository;

import com.LastByte.libreria.entitis.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Insaurralde
 */

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    
    @Query("SELECT u FROM Usuario u WHERE u.mail = :email ")
    public Usuario buscarPorEmail(@Param("email")String email);
    
    
    
    
    
    
    
}
