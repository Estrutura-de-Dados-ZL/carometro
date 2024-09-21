package com.carometro.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carometro.model.Role;
import com.carometro.model.Usuario;
import com.carometro.repository.RoleRepository;
import com.carometro.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Criar roles
        Role roleAdmin = new Role();
        roleAdmin.setNome("ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setNome("USER");
        roleRepository.save(roleUser);

        // Criar conta administrador
        Usuario administrador = new Usuario();
        administrador.setEmail("admin@admin.com");
        administrador.setSenha(passwordEncoder.encode("admin1234"));

        Role role = roleRepository.getReferenceById(1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        administrador.setRoles(roles);
        
        usuarioRepository.save(administrador);
    }

}
