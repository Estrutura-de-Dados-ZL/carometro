package com.carometro.persistence;

import java.sql.SQLException;

import com.carometro.model.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CursoDAO implements IDAO<Curso> {

  EntityManagerFactory mf = Persistence.createEntityManagerFactory("HibJPA");

  @Override
  public void criar(Curso curso) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.persist(curso);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public Curso buscar(Long cursoId) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Curso cursoEncontrado = em.find(Curso.class, cursoId);

    return cursoEncontrado;
  }

  @Override
  public void atualizar(Curso curso) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.merge(curso);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public void deletar(Curso curso) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Curso cursoEncontrado = em.find(Curso.class, curso.getCursoId());
    if (cursoEncontrado != null) {
      em.remove(cursoEncontrado);
    }
    em.getTransaction().commit();
    em.close();
  }

}
