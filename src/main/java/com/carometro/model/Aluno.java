package com.carometro.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Aluno extends Usuario implements Serializable {

  private static final long serialVersionUID = 1l;

  @Column(unique = true)
  private Long ra;

  private String foto;
  private String link;
  private String historico;
  private Boolean permissao;
  private Boolean verificado;

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
