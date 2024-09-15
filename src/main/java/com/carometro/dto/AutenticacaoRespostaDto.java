package com.carometro.dto;

import lombok.Data;

@Data
public class AutenticacaoRespostaDto {
  private String tokenAcesso;
  private String tokenTipo = "Bearer ";

  public AutenticacaoRespostaDto(String tokenAcesso) {
    this.tokenAcesso = tokenAcesso;
  }
}