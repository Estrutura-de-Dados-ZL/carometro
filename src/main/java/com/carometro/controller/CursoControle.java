package com.carometro.controller;

import java.sql.SQLException;
import java.util.List;

import com.carometro.model.Curso;
import com.carometro.persistence.CursoDAO;

public class CursoControle implements IController<Curso> {
  private CursoDAO cursoDAO = new CursoDAO();

  @Override
  public void criarRegistro(Curso curso) throws SQLException {
    cursoDAO.criar(curso);
  }

  @Override
  public Curso buscarRegistro(Long cursoId) throws SQLException {
    return cursoDAO.buscar(cursoId);
  }

  @Override
  public void atualizarRegistro(Curso curso) throws SQLException {
    cursoDAO.atualizar(curso);
  }

  @Override
  public void deletarRegistro(Curso curso) throws SQLException {
    cursoDAO.deletar(curso);
  }

  @Override
  public List<Curso> buscarTodosRegistros() throws SQLException {
    return cursoDAO.listar();
  }
}
