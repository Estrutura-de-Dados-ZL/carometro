package com.carometro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carometro.model.Aluno;
import com.carometro.model.TurmaId;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  List<Aluno> findAlunoByTurmaId(TurmaId id);
}