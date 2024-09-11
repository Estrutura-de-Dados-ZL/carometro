package com.carometro.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

  private static final long serialVersionUID = 1l;

  private Long id;
  private String texto;
}
