package com.carometro.controller;

import java.sql.SQLException;
import java.util.List;

import com.carometro.model.Turma;
import com.carometro.persistence.TurmaDAO;

public class TurmaControle implements IController<Turma> {
  private TurmaDAO turmaDAO = new TurmaDAO();

  @Override
  public void criarRegistro(Turma turma) throws SQLException {
    turmaDAO.criar(turma);
  }

  @Override
  public Turma buscarRegistro(Long turmaId) throws SQLException {
    return turmaDAO.buscar(turmaId);
  }

  @Override
  public void atualizarRegistro(Turma turma) throws SQLException {
    turmaDAO.atualizar(turma);
  }

  @Override
  public void deletarRegistro(Turma turma) throws SQLException {
    turmaDAO.deletar(turma);
  }

  @Override
  public List<Turma> buscarTodosRegistros() throws SQLException {
    return turmaDAO.listar();
  }
}
