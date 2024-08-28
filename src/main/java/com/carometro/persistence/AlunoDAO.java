package com.carometro.persistence;

import java.sql.SQLException;

import com.carometro.model.Aluno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AlunoDAO implements IDAO<Aluno> {

  EntityManagerFactory mf = Persistence.createEntityManagerFactory("HibJPA");

  @Override
  public void criar(Aluno aluno) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.persist(aluno);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public Aluno buscar(String alunoId) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Aluno alunoEncontrado = em.find(Aluno.class, alunoId);

    return alunoEncontrado;
  }

  @Override
  public void atualizar(Aluno aluno) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    em.merge(aluno);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public void deletar(Aluno aluno) throws SQLException {
    EntityManager em = mf.createEntityManager();
    em.getTransaction().begin();
    Aluno alunoEncontrado = em.find(Aluno.class, aluno.getAlunoId());
    if (alunoEncontrado != null) {
      em.remove(alunoEncontrado);
    }
    em.getTransaction().commit();
    em.close();
  }
}
