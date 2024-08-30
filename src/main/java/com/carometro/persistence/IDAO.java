package com.carometro.persistence;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
  public void criar(T t) throws SQLException;

  public T buscar(Long l) throws SQLException;

  public void atualizar(T t) throws SQLException;

  public void deletar(T t) throws SQLException;

  public List<T> listar() throws SQLException;
}
