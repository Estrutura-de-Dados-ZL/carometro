package com.carometro.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carometro.model.Role;
import com.carometro.repository.RoleRepository;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  public List<Role> buscarPeloNome(String nome) {

    Role role = roleRepository.findByNome(nome).get();
    return Collections.singletonList(role);
  }
}
