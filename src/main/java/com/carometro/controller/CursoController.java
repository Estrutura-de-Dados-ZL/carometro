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

import com.carometro.model.Curso;
import com.carometro.service.CursoService;

@RequestMapping("curso")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class CursoController {

  @Autowired
  private CursoService cursoService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Curso>> listarCursos() {
    return new ResponseEntity<>(cursoService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
    return new ResponseEntity<>(cursoService.buscarRegistro(id), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarCurso(@RequestBody Curso curso) {
    cursoService.atualizarRegistro(curso);

    return new ResponseEntity<>("Curso atualizado com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarCurso(@RequestBody Curso curso) {
    cursoService.criarRegistro(curso);

    return new ResponseEntity<>("Curso criado com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarCurso(@PathVariable Long id) {
    cursoService.deletarRegistro(id);

    return new ResponseEntity<>("Curso excluído com sucesso!", HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<String> teste() {
    return new ResponseEntity<>("Curso TESTEEEE com sucesso!", HttpStatus.OK);
  }
}
