package com.carometro;

import java.util.List;

import com.carometro.controller.AlunoControle;
import com.carometro.controller.CursoControle;
import com.carometro.controller.TurmaControle;
import com.carometro.model.Aluno;
import com.carometro.model.Curso;
import com.carometro.model.Turma;

public class Main {
    public static void main(String[] args) {

        // Teste - Curso
        validarCurso(1);
        // Teste - Turma
        validarTurma(1);
        // Teste - Aluno
        validarAluno(1);

    }

    public static void validarCurso(int mode) {
        // Validacao Curso - Adiciona, atualiza, busca e remove curso.
        CursoControle cursoControle = new CursoControle();

        if (mode == 1) {
            Curso curso = new Curso();
            // curso.setCursoId(4L);
            // curso.setCursoNome("ADS");
            // curso.setCursoNome("COMEX");
            curso.setCursoNome("LOG");

            try {
                cursoControle.criarRegistro(curso);
            } catch (Exception e) {
                System.out.println("Algo deu errado1");
                System.err.println(e);
            }
        }

        if (mode == 2) {
            try {
                Curso curso = cursoControle.buscarRegistro(1L);
                System.out.println(curso.getCursoNome());
            } catch (Exception e) {
                System.out.println("Algo deu errado2");
                System.err.println(e);
            }
        }

        if (mode == 3) {
            try {
                Curso curso = new Curso();
                curso.setCursoId(1L);
                curso.setCursoNome("TESTE"); // Mudar de ADS para TESTE
                cursoControle.atualizarRegistro(curso); // Validar no BD
            } catch (Exception e) {
                System.out.println("Algo deu errado3");
                System.err.println(e);
            }
        }

        if (mode == 4) {
            try {
                List<Curso> cursos = cursoControle.buscarTodosRegistros();
                for (Curso c : cursos) {
                    System.out.println(c.getCursoNome());
                }
            } catch (Exception e) {
                System.out.println("Algo deu errado4");
                System.err.println(e);
            }
        }

        if (mode == 5) {
            try {
                Curso curso = new Curso();
                curso.setCursoId(1L);
                cursoControle.deletarRegistro(curso);
            } catch (Exception e) {
                System.out.println("Algo deu errado5");
                System.err.println(e);
            }
        }
    }

    public static void validarTurma(int mode) {
        // Validacao Turma- Adiciona, atualiza, busca e remove turma.
        TurmaControle turmaControle = new TurmaControle();

        if (mode == 1) {
            Turma turma = new Turma();
            // turma.setTurmaId(1L);
            turma.setTurmaAno("2024");
            turma.setTurmaSemestre("2");

            try {
                turmaControle.criarRegistro(turma);
            } catch (Exception e) {
                System.out.println("Algo deu errado1");
                System.err.println(e);
            }
        }

        if (mode == 2) {
            try {
                Turma turma = turmaControle.buscarRegistro(1L);
                System.out.println(turma.getTurmaAno() + " " + turma.getTurmaSemestre());
            } catch (Exception e) {
                System.out.println("Algo deu errado2");
                System.err.println(e);
            }
        }

        if (mode == 3) {
            try {
                Turma turma = new Turma();
                turma.setTurmaId(1L);
                turma.setTurmaAno("2025");
                turma.setTurmaSemestre("1");
                turmaControle.atualizarRegistro(turma);
            } catch (Exception e) {
                System.out.println("Algo deu errado3");
                System.err.println(e);
            }
        }

        if (mode == 4) {
            try {
                List<Turma> turmas = turmaControle.buscarTodosRegistros();
                for (Turma t : turmas) {
                    System.out.println(t.getTurmaAno() + " " + t.getTurmaSemestre());
                }

            } catch (Exception e) {
                System.out.println("Algo deu errado4");
                System.err.println(e);
            }
        }

        if (mode == 5)

        {
            try {
                Turma turma = new Turma();
                turma.setTurmaId(1L);
                turmaControle.deletarRegistro(turma);
            } catch (Exception e) {
                System.out.println("Não é possível excluir turmas com alunos");
                System.err.println(e);
            }
        }
    }

    public static void validarAluno(int mode) {
        // Validacao Aluno- Adiciona, atualiza, busca e remove aluno.
        AlunoControle alunoControle = new AlunoControle();
        Turma t = new Turma();
        t.setTurmaId(1L);

        if (mode == 1) {
            Aluno aluno = new Aluno();
            aluno.setAlunoNome("Lucas");
            aluno.setAlunoComentario("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            aluno.setAlunoHistorico("Bom");
            aluno.setAlunoLink("www.Aluno.com");
            aluno.setAlunoPermissao(true);
            aluno.setTurma(t);

            try {
                alunoControle.criarRegistro(aluno);
            } catch (Exception e) {
                System.out.println("Algo deu errado1");
                System.err.println(e);
            }
        }

        if (mode == 2) {
            try {
                Aluno aluno = alunoControle.buscarRegistro(2L);
                System.out.println(aluno.getAlunoNome());
            } catch (Exception e) {
                System.out.println("Algo deu errado2");
                System.err.println(e);
            }
        }

        if (mode == 3) {
            try {
                Aluno aluno = new Aluno();
                aluno.setAlunoId(1L);
                aluno.setAlunoNome("Guilherme");
                alunoControle.atualizarRegistro(aluno);
            } catch (Exception e) {
                System.out.println("Algo deu errado3");
                System.err.println(e);
            }
        }

        if (mode == 4) {
            try {
                List<Aluno> alunos = alunoControle.buscarTodosRegistros();
                for (Aluno a : alunos) {
                    System.out.println(a.getAlunoNome());
                }
            } catch (Exception e) {
                System.out.println("Algo deu errado4");
                System.err.println(e);
            }
        }

        if (mode == 5) {
            try {
                Aluno aluno = new Aluno();
                aluno.setAlunoId(4L);
                alunoControle.deletarRegistro(aluno);
            } catch (Exception e) {
                System.out.println("Algo deu errado5");
                System.err.println(e);
            }
        }
    }
}