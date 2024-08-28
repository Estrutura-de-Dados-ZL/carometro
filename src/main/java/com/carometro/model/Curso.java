package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

  private static final long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String cursoId;
  private String cursoNome;

  public Curso() {
  }

  public Curso(String cursoId, String cursoNome) {
    this.cursoId = cursoId;
    this.cursoNome = cursoNome;
  }

  public String getCursoId() {
    return cursoId;
  }

  public void setCursoId(String cursoId) {
    this.cursoId = cursoId;
  }

  public String getCursoNome() {
    return cursoNome;
  }

  public void setCursoNome(String cursoNome) {
    this.cursoNome = cursoNome;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
