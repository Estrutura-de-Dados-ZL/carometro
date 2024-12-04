package com.carometro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.carometro.mapper.AlunoSpecification;
import com.carometro.dto.AlunoDetailsDto;
import com.carometro.dto.CadastroAlunoDto;
import com.carometro.model.Aluno;
import com.carometro.model.Role;
import com.carometro.repository.AlunoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AlunoService implements IService<Aluno, String> {

  @Autowired
  private AlunoRepository alunoRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void criarRegistro(Aluno aluno) {
    alunoRepository.save(aluno);
  }

  @Override
  public Optional<Aluno> buscarRegistro(String email) {
    Optional<Aluno> alunoEncontrado = alunoRepository.findByEmail(email);
    return alunoEncontrado;
  }

  @Override
  public void atualizarRegistro(Aluno aluno) {
    alunoRepository.save(aluno);
  }

  @Transactional
  @Override
  public void deletarRegistro(String email) {
    Optional<Aluno> aluno = alunoRepository.findByEmail(email);
    if (aluno.isPresent()) {
      // Directly delete associations from the user_role table
      aluno.get().setRoles(null);
      alunoRepository.save(aluno.get());
      alunoRepository.delete(aluno.get());
    } else {
      throw new EntityNotFoundException("Aluno não encontrado");
    }
  }
  
  @Override
  public List<Aluno> buscarTodosRegistros() {
    return alunoRepository.findAll();
  }

  public List<AlunoDetailsDto> buscarTodosRegistros(AlunoSpecification specification) {
    List<Aluno> x = alunoRepository.findAll(specification);
    return parseAlunoDto(x);
  }

  public List<AlunoDetailsDto> parseAlunoDto(List<Aluno> alunos){
    return alunos.stream().map(aluno -> new AlunoDetailsDto(aluno.getId(), aluno.getRa(), aluno.getNome(), aluno.getEmail(), aluno.getFoto(), 
    aluno.getLink(), aluno.getComentario(), aluno.getCampoLivre(), aluno.getPermissaoDados(), aluno.getPendente())).toList();
    
  }

  public boolean validarEmailExistente(CadastroAlunoDto cadastroDto) {
    return alunoRepository.existsByEmail(cadastroDto.getEmail());
  }

  public void criarAlunoRaSenhaRole(CadastroAlunoDto cadastroDto) {
    Aluno aluno = new Aluno();
    aluno.setEmail(cadastroDto.getEmail());
    aluno.setSenha(passwordEncoder.encode(cadastroDto.getSenha()));

    List<Role> roles = roleService.buscarPeloNome("USER");
    aluno.setRoles(roles);

    criarRegistro(aluno);
  }
}
