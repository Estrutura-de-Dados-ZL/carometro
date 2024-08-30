package com.carometro.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

  private static final long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long turmaId;
  private String turmaAno;
  private String turmaSemestre;

 @OneToMany(mappedBy = "turma")
  private Set<Aluno> alunos;

  public Turma() {
  }

  public Turma(Long turmaId, String turmaAno, String turmaSemestre, Set<Aluno> alunos) {
    this.turmaId = turmaId;
    this.turmaAno = turmaAno;
    this.turmaSemestre = turmaSemestre;
    this.alunos = alunos;
  }

  public Long getTurmaId() {
    return turmaId;
  }

  public void setTurmaId(Long turmaId) {
    this.turmaId = turmaId;
  }

  public String getTurmaAno() {
    return turmaAno;
  }

  public void setTurmaAno(String turmaAno) {
    this.turmaAno = turmaAno;
  }

  public String getTurmaSemestre() {
    return turmaSemestre;
  }

  public void setTurmaSemestre(String turmaSemestre) {
    this.turmaSemestre = turmaSemestre;
  }

  public Set<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(Set<Aluno> alunos) {
    this.alunos = alunos;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
