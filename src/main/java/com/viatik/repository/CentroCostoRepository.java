package com.viatik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.CentroCosto;

public interface CentroCostoRepository extends JpaRepository<CentroCosto, Integer> {
}