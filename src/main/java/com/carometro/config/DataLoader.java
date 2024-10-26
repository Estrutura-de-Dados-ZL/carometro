package com.carometro.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carometro.model.Curso;
import com.carometro.model.Role;
import com.carometro.model.Usuario;
import com.carometro.repository.CursoRepository;
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
    private CursoRepository cursoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Criar roles
        criarRole("ADMIN");
        criarRole("USER");

        // Criar conta administrador
        int[] rolesId = { 1 };
        criarUsuario("admin@admin.com", "admin1234", rolesId);

        // Criar cursos
        String[] cursos = {
                "Análise e Desenvolvimento de Sistemas",
                "AMS – Análise e Desenvolvimento de Sistemas",
                "Comércio Exterior",
                "Desenvolvimento de Produtos Plásticos",
                "Desenvolvimento de Software Multiplataforma",
                "Gestão de Recursos Humanos",
                "Logística",
                "Polímeros",
                "Gestão Empresarial"
        };
        for (String curso : cursos) {
            criarCurso(curso);
        }
    }

    private void criarRole(String roleName) {
        if (!roleRepository.existsByNome(roleName)) {
            Role role = new Role();
            role.setNome(roleName);
            roleRepository.save(role);
        }
    }

    private void criarUsuario(String email, String password, int[] rolesId) {
        if (!usuarioRepository.existsByEmail(email)) {
            Usuario administrador = new Usuario();
            administrador.setEmail(email);
            administrador.setSenha(passwordEncoder.encode(password));
            List<Role> roles = new ArrayList<>();
            for (int roleId : rolesId) {
                Role role = roleRepository.getReferenceById(roleId);
                roles.add(role);
            }
            administrador.setRoles(roles);
            usuarioRepository.save(administrador);
        }
    }

    private void criarCurso(String cursoNome) {
        if (!cursoRepository.existsByNome(cursoNome)) {
            Curso cursoNovo = new Curso();
            cursoNovo.setNome(cursoNome);
            cursoRepository.save(cursoNovo);
        }
    }
}
