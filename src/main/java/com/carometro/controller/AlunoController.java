package com.carometro.controller;

import java.util.List;

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

import com.carometro.model.Aluno;
import com.carometro.service.AlunoService;

@RequestMapping("aluno")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Aluno>> listarAlunos() {
    return new ResponseEntity<>(alunoService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
    return new ResponseEntity<>(alunoService.buscarRegistro(id), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarAluno(@RequestBody Aluno aluno) {
    alunoService.atualizarRegistro(aluno);

    return new ResponseEntity<>("Aluno atualizado com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarAluno(@RequestBody Aluno aluno) {
    alunoService.criarRegistro(aluno);

    return new ResponseEntity<>("Aluno criado com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarAluno(@PathVariable Long id) {
    alunoService.deletarRegistro(id);

    return new ResponseEntity<>("Aluno excluído com sucesso!", HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<String> teste() {
    return new ResponseEntity<>("Aluno TESTEEEE com sucesso!", HttpStatus.OK);
  }
}
