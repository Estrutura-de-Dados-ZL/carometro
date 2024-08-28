package com.carometro.persistence;

import java.sql.SQLException;

public interface IDAO<T> {
  public void criar(T t) throws SQLException;

  public T buscar(String s) throws SQLException;

  public void atualizar(T t) throws SQLException;

  public void deletar(T t) throws SQLException;
}
