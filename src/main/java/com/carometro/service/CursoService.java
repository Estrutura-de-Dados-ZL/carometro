package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Curso;
import com.carometro.repository.CursoRepository;

@Service
public class CursoService implements IService<Curso> {

  @Autowired
  private CursoRepository cursoRepository;

  @Override
  public void criarRegistro(Curso curso) {
    cursoRepository.save(curso);
  }

  @Override
  public Curso buscarRegistro(Long cursoId) {
    Optional<Curso> cursoEncontrado = cursoRepository.findById(cursoId);
    return cursoEncontrado.orElse(null);
  }

  @Override
  public void atualizarRegistro(Curso curso) {
    cursoRepository.save(curso);
  }

  @Override
  public void deletarRegistro(Long id) {
    Curso c = new Curso();
    c.setCursoId(id);

    cursoRepository.delete(c);
  }

  @Override
  public List<Curso> buscarTodosRegistros() {
    return cursoRepository.findAll();
  }
}
