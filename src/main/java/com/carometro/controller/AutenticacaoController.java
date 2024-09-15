package com.carometro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carometro.dto.ApiRespostaDto;
import com.carometro.dto.AutenticacaoRespostaDto;
import com.carometro.dto.CadastroAlunoDto;
import com.carometro.dto.LoginAlunoDto;
import com.carometro.security.JWTGerador;
import com.carometro.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private JWTGerador jwtGerador;

  @PostMapping("/login")
  public ResponseEntity<ApiRespostaDto<AutenticacaoRespostaDto>> login(
      @Valid @RequestBody LoginAlunoDto loginAlunoDto) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginAlunoDto.getEmail(),
            loginAlunoDto.getSenha()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtGerador.generateToken(authentication);

    List<String> mensagensSucesso = List.of("Login efetuado com sucesso!");
    AutenticacaoRespostaDto autenticacaoRespostaDto = new AutenticacaoRespostaDto(token);
    ApiRespostaDto<AutenticacaoRespostaDto> respostaSucesso = new ApiRespostaDto<>(autenticacaoRespostaDto,
        mensagensSucesso);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PostMapping("/cadastrar/aluno")
  public ResponseEntity<ApiRespostaDto<String>> cadastrarAluno(@Valid @RequestBody CadastroAlunoDto cadastroDto) {

    if (alunoService.validarEmailExistente(cadastroDto)) {
      List<String> mensagensErro = List.of("Email já cadastrado!");
      ApiRespostaDto<String> responseErro = new ApiRespostaDto<>(null, mensagensErro);
      return new ResponseEntity<>(responseErro, HttpStatus.BAD_REQUEST);
    }

    alunoService.criarAlunoRaSenhaRole(cadastroDto);

    List<String> mensagensSucesso = List.of("Aluno cadastrado com sucesso!");
    ApiRespostaDto<String> respostaSucesso = new ApiRespostaDto<>(null, mensagensSucesso);
    return new ResponseEntity<>(respostaSucesso, HttpStatus.CREATED);
  }
}
