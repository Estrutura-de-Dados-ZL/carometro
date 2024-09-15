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
import com.carometro.model.Experiencia;
import com.carometro.service.ExperienciaService;

@RequestMapping("experiencia")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ExperienciaController {

  @Autowired
  private ExperienciaService experienciaService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Experiencia>> listarExperiencias() {
    return new ResponseEntity<>(experienciaService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiRespostaDto<Experiencia>> buscarExperienciaPorId(@PathVariable Long id) {
    Optional<Experiencia> experiencia = experienciaService.buscarRegistro(id);

    if (!experiencia.isPresent()) {
      List<String> mensagensErro = List.of("Experiência não encontrado!");
      ApiRespostaDto<Experiencia> respostaErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    List<String> mensagensErro = List.of("Experiência encontrado!");
    ApiRespostaDto<Experiencia> respostaSucesso = new ApiRespostaDto<>(experiencia.get(), mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarExperiencia(@RequestBody Experiencia experiencia) {
    experienciaService.atualizarRegistro(experiencia);

    return new ResponseEntity<>("Experiência atualizada com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarExperiencia(@RequestBody Experiencia experiencia) {
    experienciaService.criarRegistro(experiencia);

    return new ResponseEntity<>("Experiência criada com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarExperiencia(@PathVariable Long id) {
    experienciaService.deletarRegistro(id);

    return new ResponseEntity<>("Experiência excluída com sucesso!", HttpStatus.OK);
  }
}
