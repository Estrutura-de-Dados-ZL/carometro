package com.carometro.controller;

import java.sql.SQLException;
import java.util.List;

public interface IController<T> {
  public void criarRegistro(T t) throws SQLException;

  public T buscarRegistro(Long id) throws SQLException;

  public void atualizarRegistro(T t) throws SQLException;

  public void deletarRegistro(T t) throws SQLException;

  public List<T> buscarTodosRegistros() throws SQLException;
}
