package com.carometro.repository;

import org.springframework.stereotype.Repository;

import com.carometro.model.Curso;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);

    boolean existsByNome(String nome);
}