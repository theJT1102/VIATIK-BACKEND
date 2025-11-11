package com.viatik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    // Spring Data genera la consulta automáticamente a partir del nombre
    List<Articulo> findByNombreContainingIgnoreCaseOrCodigoContainingIgnoreCase(String nombre, String codigo);
}
