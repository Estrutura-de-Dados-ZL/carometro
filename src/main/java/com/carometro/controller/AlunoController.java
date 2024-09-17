package com.carometro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carometro.dto.AlunoDto;
import com.carometro.dto.ApiRespostaDto;
import com.carometro.mapper.AlunoMapper;
import com.carometro.model.Aluno;
import com.carometro.security.JWTGerador;
import com.carometro.service.AlunoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RequestMapping("aluno")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private AlunoMapper alunoMapper;

  @Autowired
  JWTGerador jwtGerador;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Aluno>> listarAlunos() {
    return new ResponseEntity<>(alunoService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{email}")
  public ResponseEntity<ApiRespostaDto<Aluno>> buscarAlunoPorId(@PathVariable String email) {
    Optional<Aluno> aluno = alunoService.buscarRegistro(email);

    if (!aluno.isPresent()) {
      List<String> mensagensErro = List.of("Aluno não encontrado!");
      ApiRespostaDto<Aluno> respostaErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    List<String> mensagensErro = List.of("Aluno encontrado!");
    ApiRespostaDto<Aluno> respostaSucesso = new ApiRespostaDto<>(aluno.get(), mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ApiRespostaDto<String>> atualizarAluno(@Valid @RequestBody(required = false) AlunoDto dto,
      HttpServletRequest request) {

    Optional<Aluno> aluno = alunoService.buscarRegistro(dto.getEmail());

    if (!aluno.isPresent()) {
      List<String> mensagensErro = List.of("Aluno não encontrado!");
      ApiRespostaDto<String> responseErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(responseErro, HttpStatus.BAD_REQUEST);
    }

    alunoMapper.updateAlunoFromDto(dto, aluno.get());
    alunoService.atualizarRegistro(aluno.get());

    List<String> mensagensErro = List.of("Aluno atualizado com sucesso!");
    ApiRespostaDto<String> respostaSucesso = new ApiRespostaDto<>(null, mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarAluno(@RequestBody Aluno aluno) {
    alunoService.criarRegistro(aluno);

    return new ResponseEntity<>("Aluno criado com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<String> deletarAluno(@PathVariable String email) {
    alunoService.deletarRegistro(email);

    return new ResponseEntity<>("Aluno excluído com sucesso!", HttpStatus.OK);
  }
}
