package com.carometro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carometro.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByNome(String nome);
}