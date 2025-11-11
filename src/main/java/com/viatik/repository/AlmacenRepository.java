package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
}