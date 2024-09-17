package com.carometro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carometro.model.ExperienciaAluno;

@Repository
public interface ExperienciaAlunoRepository extends JpaRepository<ExperienciaAluno, Long> {

}
