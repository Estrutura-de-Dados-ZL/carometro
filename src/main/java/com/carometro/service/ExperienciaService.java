package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Experiencia;
import com.carometro.repository.ExperienciaRepository;

@Service
public class ExperienciaService implements IService<Experiencia, Long> {

  @Autowired
  private ExperienciaRepository experienciaRepository;

  @Override
  public void criarRegistro(Experiencia experiencia) {
    experienciaRepository.save(experiencia);
  }

  @Override
  public Experiencia buscarRegistro(Long experienciaId) {
    Optional<Experiencia> experienciaEncontrado = experienciaRepository.findById(experienciaId);
    return experienciaEncontrado.orElse(null);
  }

  @Override
  public void atualizarRegistro(Experiencia experiencia) {
    experienciaRepository.save(experiencia);
  }

  @Override
  public void deletarRegistro(Long id) {
    Experiencia e = new Experiencia();
    e.setId(id);

    experienciaRepository.delete(e);
  }

  @Override
  public List<Experiencia> buscarTodosRegistros() {
    return experienciaRepository.findAll();
  }
}
