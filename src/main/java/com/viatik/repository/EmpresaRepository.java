package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}