package com.carometro.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
