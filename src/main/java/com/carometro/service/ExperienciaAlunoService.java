package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.ExperienciaAluno;
import com.carometro.repository.ExperienciaAlunoRepository;

@Service
public class ExperienciaAlunoService implements IService<ExperienciaAluno, Long> {

  @Autowired
  private ExperienciaAlunoRepository experienciaAlunoRepository;

  @Override
  public void criarRegistro(ExperienciaAluno experienciaAluno) {
    experienciaAlunoRepository.save(experienciaAluno);
  }

  @Override
  public Optional<ExperienciaAluno> buscarRegistro(Long experienciaAlunoId) {
    Optional<ExperienciaAluno> experienciaAlunoEncontrado = experienciaAlunoRepository.findById(experienciaAlunoId);
    return experienciaAlunoEncontrado;
  }

  @Override
  public void atualizarRegistro(ExperienciaAluno experiencia) {
    experienciaAlunoRepository.save(experiencia);
  }

  @Override
  public void deletarRegistro(Long id) {
    ExperienciaAluno experienciaAluno = new ExperienciaAluno();
    experienciaAluno.setId(id);

    experienciaAlunoRepository.delete(experienciaAluno);
  }

  @Override
  public List<ExperienciaAluno> buscarTodosRegistros() {
    return experienciaAlunoRepository.findAll();
  }
}
