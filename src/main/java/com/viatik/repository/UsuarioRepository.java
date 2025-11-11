package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Para el login
    Usuario findByUsername(String username);
    
    
}