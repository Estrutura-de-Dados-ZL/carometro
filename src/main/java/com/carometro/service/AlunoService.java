package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Aluno;
import com.carometro.repository.AlunoRepository;

@Service
public class AlunoService implements IService<Aluno, Long> {

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public void criarRegistro(Aluno aluno) {
    alunoRepository.save(aluno);
  }

  @Override
  public Aluno buscarRegistro(Long alunoId) {
    Optional<Aluno> alunoEncontrado = alunoRepository.findById(alunoId);
    return alunoEncontrado.orElse(null);
  }

  @Override
  public void atualizarRegistro(Aluno aluno) {
    alunoRepository.save(aluno);
  }

  @Override
  public void deletarRegistro(Long id) {
    Aluno t = new Aluno();
    t.setId(id);

    alunoRepository.delete(t);
  }

  @Override
  public List<Aluno> buscarTodosRegistros() {
    return alunoRepository.findAll();
  }
}
