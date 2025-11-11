package com.viatik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viatik.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findById(Integer id);
}
