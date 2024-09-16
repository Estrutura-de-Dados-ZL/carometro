package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
}
