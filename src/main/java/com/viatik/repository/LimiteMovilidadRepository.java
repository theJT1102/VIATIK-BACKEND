package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.LimiteMovilidad;

public interface LimiteMovilidadRepository extends JpaRepository<LimiteMovilidad, Integer> {
    // Para buscar el límite específico de una categoría
    LimiteMovilidad findByIdCategoria(Integer idCategoria);
}