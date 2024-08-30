package com.carometro.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

  private static final long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long alunoId;
  private String alunoNome;
  private String alunoFoto;
  private String alunoLink;
  private String alunoHistorico;
  private String alunoComentario;
  private Boolean alunoPermissao;

  @ManyToOne
  @JoinColumn(name = "turma_turmaId")
  private Turma turma;

  public Aluno() {
  }

  public Aluno(Long alunoId, String alunoNome, String alunoFoto, String alunoLink, String alunoHistorico,
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

  public Long getAlunoId() {
    return alunoId;
  }

  public void setAlunoId(Long alunoId) {
    this.alunoId = alunoId;
  }

  public String getAlunoNome() {
    return alunoNome;
  }

  public void setAlunoNome(String alunoNome) {
    this.alunoNome = alunoNome;
  }

  public String getAlunoFoto() {
    return alunoFoto;
  }

  public void setAlunoFoto(String alunoFoto) {
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
