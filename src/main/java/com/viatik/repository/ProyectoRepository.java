package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}