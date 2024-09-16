package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Turma;
import com.carometro.model.TurmaId;
import com.carometro.repository.TurmaRepository;

@Service
public class TurmaService implements IService<Turma, TurmaId> {

  @Autowired
  private TurmaRepository turmaRepository;

  @Override
  public void criarRegistro(Turma turma) {
    turmaRepository.save(turma);
  }

  @Override
  public Optional<Turma> buscarRegistro(TurmaId turmaId) {
    Optional<Turma> turmaEncontrado = turmaRepository.findById(turmaId);
    return turmaEncontrado;
  }

  @Override
  public void atualizarRegistro(Turma turma) {
    turmaRepository.save(turma);
  }

  @Override
  public void deletarRegistro(TurmaId id) {
    Turma t = new Turma();
    t.setId(id);

    turmaRepository.delete(t);
  }

  @Override
  public List<Turma> buscarTodosRegistros() {
    return turmaRepository.findAll();
  }
}
