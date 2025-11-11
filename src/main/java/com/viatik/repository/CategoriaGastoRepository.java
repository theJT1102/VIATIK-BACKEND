package com.viatik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.CategoriaGasto;

public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Integer> {
    // Para cargar categorías por tipo
    List<CategoriaGasto> findByTipoCategoria(CategoriaGasto.TipoCategoria tipo);
}