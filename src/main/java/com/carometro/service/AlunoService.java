package com.carometro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carometro.dto.CadastroAlunoDto;
import com.carometro.model.Aluno;
import com.carometro.model.Role;
import com.carometro.repository.AlunoRepository;

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

  @Override
  public void deletarRegistro(String email) {
    Aluno aluno = new Aluno();
    aluno.setEmail(email);

    alunoRepository.delete(aluno);
  }

  @Override
  public List<Aluno> buscarTodosRegistros() {
    return alunoRepository.findAll();
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
