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
import com.carometro.model.ExperienciaAluno;
import com.carometro.service.ExperienciaAlunoService;

@RequestMapping("experiencia")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ExperienciaAlunoController {

  @Autowired
  private ExperienciaAlunoService experienciaAlunoService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<ExperienciaAluno>> listarExperiencias() {
    return new ResponseEntity<>(experienciaAlunoService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiRespostaDto<ExperienciaAluno>> buscarExperienciaPorId(@PathVariable Long id) {
    Optional<ExperienciaAluno> experienciaAluno = experienciaAlunoService.buscarRegistro(id);

    if (!experienciaAluno.isPresent()) {
      List<String> mensagensErro = List.of("Experiência não encontrado!");
      ApiRespostaDto<ExperienciaAluno> respostaErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    List<String> mensagensErro = List.of("Experiência encontrado!");
    ApiRespostaDto<ExperienciaAluno> respostaSucesso = new ApiRespostaDto<>(experienciaAluno.get(), mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarExperiencia(@RequestBody ExperienciaAluno experienciaAluno) {
    experienciaAlunoService.atualizarRegistro(experienciaAluno);

    return new ResponseEntity<>("Experiência atualizada com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarExperiencia(@RequestBody ExperienciaAluno experienciaAluno) {
    experienciaAlunoService.criarRegistro(experienciaAluno);

    return new ResponseEntity<>("Experiência criada com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarExperiencia(@PathVariable Long id) {
    experienciaAlunoService.deletarRegistro(id);

    return new ResponseEntity<>("Experiência excluída com sucesso!", HttpStatus.OK);
  }
}
