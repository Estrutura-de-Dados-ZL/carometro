package com.carometro.repository;

import org.springframework.stereotype.Repository;

import com.carometro.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNome(String nome);
}