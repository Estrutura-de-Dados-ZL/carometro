package com.carometro.dto;

import com.carometro.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDetailsDto {

  private Long id;
  private String ra;
  private String nome;
  private String email;
  private String foto;
  private String link;
  private String comentario;
  private String campoLivre;
  private Boolean permissaoDados;
  private Boolean pendente;
}
