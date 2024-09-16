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

import com.carometro.dto.ApiRespostaDto;
import com.carometro.model.Turma;
import com.carometro.model.TurmaId;
import com.carometro.service.TurmaService;

@RequestMapping("turma")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class TurmaController {

  @Autowired
  private TurmaService turmaService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Turma>> listarTurmas() {
    return new ResponseEntity<>(turmaService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{cursoId}/{ano}/{semestre}")
  public ResponseEntity<ApiRespostaDto<Turma>> buscarTurmaPorId(
      @PathVariable Long cursoId,
      @PathVariable String ano,
      @PathVariable String semestre) {

    TurmaId turmaId = new TurmaId(
        cursoId,
        ano,
        semestre);
    Optional<Turma> turma = turmaService.buscarRegistro(turmaId);

    if (!turma.isPresent()) {
      List<String> mensagensErro = List.of("Experiência não encontrado!");
      ApiRespostaDto<Turma> respostaErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    List<String> mensagensErro = List.of("Experiência encontrado!");
    ApiRespostaDto<Turma> respostaSucesso = new ApiRespostaDto<>(turma.get(), mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarTurma(@RequestBody Turma turma) {
    turmaService.atualizarRegistro(turma);

    return new ResponseEntity<>("Turma atualizada com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarTurma(@RequestBody Turma turma) {
    turmaService.criarRegistro(turma);

    return new ResponseEntity<>("Turma criada com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{cursoId}/{ano}/{semestre}")
  public ResponseEntity<String> deletarTurma(
      @PathVariable Long cursoId,
      @PathVariable String ano,
      @PathVariable String semestre) {
    TurmaId turmaId = new TurmaId(
        cursoId,
        ano,
        semestre);
    turmaService.deletarRegistro(turmaId);

    return new ResponseEntity<>("Turma excluída com sucesso!", HttpStatus.OK);
  }
}
