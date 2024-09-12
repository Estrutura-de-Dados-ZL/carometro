package com.carometro.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
  private Long id;
  private String nome;
  private String foto;
  private String link;
  private String historico;
  private Boolean permissao;

  @ManyToOne
  @JoinColumns({
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id"),
    @JoinColumn(name = "ano", referencedColumnName = "ano"),
    @JoinColumn(name = "semestre", referencedColumnName = "semestre")
  })
  private Turma turma;

  @OneToOne
  @JoinColumn(name = "comentario_id")
  private Comentario comentario;

  @OneToMany
  @JoinColumn(name = "aluno_id")
  private List<Experiencia> experiencias;
}
