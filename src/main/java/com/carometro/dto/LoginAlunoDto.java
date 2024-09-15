package com.carometro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginAlunoDto {

  @NotNull(message = "O Email não pode ser nulo.")
  private String email;

  @NotNull(message = "A senha não pode ser nulo.")
  private String senha;
}
