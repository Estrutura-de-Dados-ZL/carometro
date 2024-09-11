package com.carometro.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
  public void criarRegistro(T t) throws SQLException;

  public T buscarRegistro(Long id) throws SQLException;

  public void atualizarRegistro(T t) throws SQLException;

  public void deletarRegistro(Long id) throws SQLException;

  public List<T> buscarTodosRegistros() throws SQLException;
}
