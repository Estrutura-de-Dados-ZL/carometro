package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Comentario;
import com.carometro.repository.ComentarioRepository;

@Service
public class ComentarioService implements IService<Comentario, Long> {

  @Autowired
  private ComentarioRepository comentarioRepository;

  @Override
  public void criarRegistro(Comentario comentario) {
    comentarioRepository.save(comentario);
  }

  @Override
  public Comentario buscarRegistro(Long comentarioId) {
    Optional<Comentario> comentarioEncontrado = comentarioRepository.findById(comentarioId);
    return comentarioEncontrado.orElse(null);
  }

  @Override
  public void atualizarRegistro(Comentario comentario) {
    comentarioRepository.save(comentario);
  }

  @Override
  public void deletarRegistro(Long id) {
    Comentario c = new Comentario();
    c.setId(id);

    comentarioRepository.delete(c);
  }

  @Override
  public List<Comentario> buscarTodosRegistros() {
    return comentarioRepository.findAll();
  }
}
