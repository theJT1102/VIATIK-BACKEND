package com.viatik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viatik.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    // Métodos personalizados si son necesarios, ej:
    List<Perfil> findByIdEmpresa(Integer idEmpresa);
}