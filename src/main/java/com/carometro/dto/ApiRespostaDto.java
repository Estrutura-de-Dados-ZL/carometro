package com.carometro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiRespostaDto<T> {

  private T data;
  private List<String> mensagens;

  public ApiRespostaDto(List<String> mensagens) {
    this.mensagens = mensagens;
  }
}
