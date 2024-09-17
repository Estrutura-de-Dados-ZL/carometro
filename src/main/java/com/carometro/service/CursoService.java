package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Curso;
import com.carometro.repository.CursoRepository;

@Service
public class CursoService implements IService<Curso, Long> {

  @Autowired
  private CursoRepository cursoRepository;

  @Override
  public void criarRegistro(Curso curso) {
    cursoRepository.save(curso);
  }

  @Override
  public Optional<Curso> buscarRegistro(Long cursoId) {
    Optional<Curso> cursoEncontrado = cursoRepository.findById(cursoId);
    return cursoEncontrado;
  }

  @Override
  public void atualizarRegistro(Curso curso) {
    cursoRepository.save(curso);
  }

  @Override
  public void deletarRegistro(Long id) {
    Curso curso = new Curso();
    curso.setId(id);

    cursoRepository.delete(curso);
  }

  @Override
  public List<Curso> buscarTodosRegistros() {
    return cursoRepository.findAll();
  }
}
