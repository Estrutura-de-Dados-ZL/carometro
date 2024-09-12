package com.carometro.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T, Y> {
  public void criarRegistro(T t) throws SQLException;

  public T buscarRegistro(Y y) throws SQLException;

  public void atualizarRegistro(T t) throws SQLException;

  public void deletarRegistro(Y y) throws SQLException;

  public List<T> buscarTodosRegistros() throws SQLException;
}
