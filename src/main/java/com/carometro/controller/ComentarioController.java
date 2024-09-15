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
import com.carometro.model.Comentario;
import com.carometro.service.ComentarioService;

@RequestMapping("comentario")
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ComentarioController {

  @Autowired
  private ComentarioService comentarioService;

  @GetMapping("/buscarTodos")
  public ResponseEntity<List<Comentario>> listarComentarios() {
    return new ResponseEntity<>(comentarioService.buscarTodosRegistros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiRespostaDto<Comentario>> buscarComentarioPorId(@PathVariable Long id) {
    Optional<Comentario> comentario = comentarioService.buscarRegistro(id);

    if (!comentario.isPresent()) {
      List<String> mensagensErro = List.of("Comentário não encontrado!");
      ApiRespostaDto<Comentario> respostaErro = new ApiRespostaDto<>(null, mensagensErro);

      return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    List<String> mensagensErro = List.of("Comentário encontrado!");
    ApiRespostaDto<Comentario> respostaSucesso = new ApiRespostaDto<>(comentario.get(), mensagensErro);

    return new ResponseEntity<>(respostaSucesso, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<String> atualizarComentario(@RequestBody Comentario comentario) {
    comentarioService.atualizarRegistro(comentario);

    return new ResponseEntity<>("Comentario atualizado com sucesso!", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> criarComentario(@RequestBody Comentario comentario) {
    comentarioService.criarRegistro(comentario);

    return new ResponseEntity<>("Comentario criado com sucesso!", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarComentario(@PathVariable Long id) {
    comentarioService.deletarRegistro(id);

    return new ResponseEntity<>("Comentario excluído com sucesso!", HttpStatus.OK);
  }
}
