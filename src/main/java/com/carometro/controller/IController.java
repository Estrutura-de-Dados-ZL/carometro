package com.carometro.controller;

import java.sql.SQLException;

public interface IController<T> {
  public void criarRegistro(T t) throws SQLException;

  public T buscarRegistro(String id) throws SQLException;

  public void atualizarRegistro(T t) throws SQLException;

  public void deletarRegistro(T t) throws SQLException;
}
