package com.carometro.persistence;

import java.sql.SQLException;
import java.util.List;

import com.carometro.model.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

  @Override
  public List<Curso> listar() throws SQLException {
    EntityManager em = mf.createEntityManager();
    List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    em.close();
    return cursos;
  }
}
