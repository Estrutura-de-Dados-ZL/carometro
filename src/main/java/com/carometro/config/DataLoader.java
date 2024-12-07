package com.carometro.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carometro.model.Aluno;
import com.carometro.model.Curso;
import com.carometro.model.Role;
import com.carometro.model.Turma;
import com.carometro.model.TurmaId;
import com.carometro.model.Usuario;
import com.carometro.repository.AlunoRepository;
import com.carometro.repository.CursoRepository;
import com.carometro.repository.RoleRepository;
import com.carometro.repository.TurmaRepository;
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
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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

        // Criar turmas
        String ano = "2024";
        String semestre = "1";
        criarTurma(ano, semestre);

        // Criar alunos
        int[] rolesAlunoId = { 2 };
        String[][] alunos = {
                { "jane.doe@example.com", "Jane Doe", "senha123", "Campo livre 1", "Comentário 1",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://jane-link.com", "RA12345", "1", "2024", "1" },
                { "mary.smith@example.com", "Mary Smith", "senha456", "Campo livre 2", "Comentário 2",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://mary-link.com", "RA12346", "2", "2024", "1" },
                { "lisa.jones@example.com", "Lisa Jones", "senha789", "Campo livre 3", "Comentário 3",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://lisa-link.com", "RA12347", "3", "2024", "1" },
                { "susan.brown@example.com", "Susan Brown", "senha101", "Campo livre 4", "Comentário 4",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://susan-link.com", "RA12348", "4", "2024", "1" },
                { "emily.johnson@example.com", "Emily Johnson", "senha202", "Campo livre 5", "Comentário 5",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://emily-link.com", "RA12349", "5", "2024", "1" },
                { "anna.martin@example.com", "Anna Martin", "senha303", "Campo livre 6", "Comentário 6",
                        "https://i.pinimg.com/236x/19/bd/eb/19bdeb93ad73ce5ead4800d254c51008.jpg",
                        "https://anna-link.com", "RA12350", "6", "2024", "1" },
                { "john.doe@example.com", "John Doe", "senha123", "Campo livre 1", "Comentário 1",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://john-link.com", "RA22345", "7", "2024", "1" },
                { "mark.smith@example.com", "Mark Smith", "senha456", "Campo livre 2", "Comentário 2",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://mark-link.com", "RA22346", "8", "2024", "1" },
                { "paul.jones@example.com", "Paul Jones", "senha789", "Campo livre 3", "Comentário 3",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://paul-link.com", "RA22347", "9", "2024", "1" },
                { "david.brown@example.com", "David Brown", "senha101", "Campo livre 4", "Comentário 4",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://david-link.com", "RA22348", "1", "2024", "1" },
                { "james.johnson@example.com", "James Johnson", "senha202", "Campo livre 5", "Comentário 5",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://james-link.com", "RA22349", "2", "2024", "1" },
                { "michael.martin@example.com", "Michael Martin", "senha303", "Campo livre 6", "Comentário 6",
                        "https://img.freepik.com/fotos-gratis/feche-o-retrato-de-um-rosto-jovem-barbudo_171337-2887.jpg?semt=ais_hybrid",
                        "https://michael-link.com", "RA22350", "3", "2024", "1" }
        };
        for (String[] aluno : alunos) {
            atualizarAluno(aluno, rolesAlunoId);
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
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(passwordEncoder.encode(password));
            List<Role> roles = new ArrayList<>();
            for (int roleId : rolesId) {
                Role role = roleRepository.getReferenceById(roleId);
                roles.add(role);
            }
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
        }
    }

    private void criarCurso(String cursoNome) {
        if (!cursoRepository.existsByNome(cursoNome)) {
            Curso cursoNovo = new Curso();
            cursoNovo.setNome(cursoNome);
            cursoRepository.save(cursoNovo);
        }
    }

    private void criarTurma(String ano, String semestre) {
        List<Curso> cursos = cursoRepository.findAll();
        for (Curso curso : cursos) {
            TurmaId turmaId = new TurmaId(curso.getId(), ano, semestre);
            if (!turmaRepository.existsById(turmaId)) {
                Turma turma = new Turma(turmaId, curso);
                turmaRepository.save(turma);
            }
        }
    }

    private void atualizarAluno(String[] alunoData, int[] rolesId) {
        if (!usuarioRepository.existsByEmail(alunoData[0])) {
            Aluno aluno = new Aluno();
            aluno.setEmail(alunoData[0]);
            aluno.setSenha(passwordEncoder.encode(alunoData[2]));
            aluno.setNome(alunoData[1]);
            List<Role> roles = new ArrayList<>();
            for (int roleId : rolesId) {
                Role role = roleRepository.getReferenceById(roleId);
                roles.add(role);
            }
            aluno.setRoles(roles);

            aluno.setCampoLivre(alunoData[3]);
            aluno.setComentario(alunoData[4]);
            aluno.setFoto(alunoData[5]);
            aluno.setLink(alunoData[6]);
            aluno.setRa(alunoData[7]);
            aluno.setPendente(false);
            aluno.setPermissaoDados(true);

            // aloca turma
            TurmaId turmaId = new TurmaId(Long.parseLong(alunoData[8]), alunoData[9], alunoData[10]);
            Optional<Turma> turma = turmaRepository.findById(turmaId);
            if (turma.isPresent())
                aluno.setTurma(turma.get());

            alunoRepository.save(aluno);
        }
    }
}
