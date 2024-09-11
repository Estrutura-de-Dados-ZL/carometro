package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
