package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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

  @EmbeddedId
  private TurmaId id;

  @ManyToOne
  @MapsId("cursoId")
  @JoinColumn(name = "curso_id")
  private Curso curso;
}
