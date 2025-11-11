package com.viatik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viatik.entity.Fondo;

@Repository
public interface FondoRepository extends JpaRepository<Fondo, Integer> {
    // Para el flujo de viáticos:
    List<Fondo> findByIdRendidorAndEstadoFondo(Integer idRendidor, Fondo.EstadoFondo estado);
}