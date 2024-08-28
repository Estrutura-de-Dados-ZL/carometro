package com.carometro.model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

  private static final long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String alunoId;
  private String alunoNome;
  private Serial alunoFoto;
  private String alunoLink;
  private String alunoHistorico;
  private String alunoComentario;
  private Boolean alunoPermissao;

  @OneToOne(mappedBy = "turma")
  private Turma turma;

  public Aluno() {
  }

  public Aluno(String alunoId, String alunoNome, Serial alunoFoto, String alunoLink, String alunoHistorico,
      String alunoComentario, Boolean alunoPermissao, Turma turma) {
    this.alunoId = alunoId;
    this.alunoNome = alunoNome;
    this.alunoFoto = alunoFoto;
    this.alunoLink = alunoLink;
    this.alunoHistorico = alunoHistorico;
    this.alunoComentario = alunoComentario;
    this.alunoPermissao = alunoPermissao;
    this.turma = turma;
  }

  public String getAlunoId() {
    return alunoId;
  }

  public void setAlunoId(String alunoId) {
    this.alunoId = alunoId;
  }

  public String getAlunoNome() {
    return alunoNome;
  }

  public void setAlunoNome(String alunoNome) {
    this.alunoNome = alunoNome;
  }

  public Serial getAlunoFoto() {
    return alunoFoto;
  }

  public void setAlunoFoto(Serial alunoFoto) {
    this.alunoFoto = alunoFoto;
  }

  public String getAlunoLink() {
    return alunoLink;
  }

  public void setAlunoLink(String alunoLink) {
    this.alunoLink = alunoLink;
  }

  public String getAlunoHistorico() {
    return alunoHistorico;
  }

  public void setAlunoHistorico(String alunoHistorico) {
    this.alunoHistorico = alunoHistorico;
  }

  public String getAlunoComentario() {
    return alunoComentario;
  }

  public void setAlunoComentario(String alunoComentario) {
    this.alunoComentario = alunoComentario;
  }

  public Boolean getAlunoPermissao() {
    return alunoPermissao;
  }

  public void setAlunoPermissao(Boolean alunoPermissao) {
    this.alunoPermissao = alunoPermissao;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
