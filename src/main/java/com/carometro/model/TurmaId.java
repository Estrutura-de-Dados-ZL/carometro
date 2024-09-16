package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class TurmaId implements Serializable {

  private static final long serialVersionUID = 1l;

  @Column(name = "curso_id")
  private Long cursoId;

  @Column(name = "ano")
  private String ano;

  @Column(name = "semestre")
  private String semestre;
}
