package com.carometro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {

  @NotNull
  private Long id;
  private String ra;
  private String nome;

  @NotNull
  private String email;
  private String senha;
  private String foto;
  private String link;
  private String historico;
  private Boolean permissao;
  private Boolean verificado;
}
