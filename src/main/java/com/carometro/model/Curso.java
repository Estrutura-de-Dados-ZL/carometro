package com.carometro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

  private static final long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cursoId;
  private String cursoNome;

  public Curso() {
  }

  public Curso(Long cursoId, String cursoNome) {
    this.cursoId = cursoId;
    this.cursoNome = cursoNome;
  }

  public Long getCursoId() {
    return cursoId;
  }

  public void setCursoId(Long cursoId) {
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
