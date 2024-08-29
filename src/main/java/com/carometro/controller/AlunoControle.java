package com.carometro.controller;

import java.sql.SQLException;

import com.carometro.model.Aluno;
import com.carometro.persistence.AlunoDAO;

public class AlunoControle implements IController<Aluno> {
  private AlunoDAO alunoDAO = new AlunoDAO();

  @Override
  public void criarRegistro(Aluno aluno) throws SQLException {
    alunoDAO.criar(aluno);
  }

  @Override
  public Aluno buscarRegistro(Long alunoId) throws SQLException {
    return alunoDAO.buscar(alunoId);
  }

  @Override
  public void atualizarRegistro(Aluno aluno) throws SQLException {
    alunoDAO.atualizar(aluno);
  }

  @Override
  public void deletarRegistro(Aluno aluno) throws SQLException {
    alunoDAO.deletar(aluno);
  }
}
