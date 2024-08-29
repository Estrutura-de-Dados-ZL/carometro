package com.carometro.persistence;

import java.sql.SQLException;

import com.carometro.model.Turma;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TurmaDAO implements IDAO<Turma> {

  EntityManagerFactory mf = Persistence.createEntityManagerFactory("HibJPA");

  @Override
  public void criar(Turma turma) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.persist(turma);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public Turma buscar(Long turmaId) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Turma turmaEncontrada = em.find(Turma.class, turmaId);

    return turmaEncontrada;
  }

  @Override
  public void atualizar(Turma turma) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.merge(turma);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public void deletar(Turma turma) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Turma turmaEncontrada = em.find(Turma.class, turma.getTurmaId());
    if (turmaEncontrada != null) {
      em.remove(turmaEncontrada);
    }
    em.getTransaction().commit();
    em.close();
  }

}
