package com.carometro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carometro.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}