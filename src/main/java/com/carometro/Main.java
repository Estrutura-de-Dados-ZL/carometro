package com.carometro;

import java.util.List;

import com.carometro.controller.CursoControle;
import com.carometro.model.Curso;

public class Main {
    public static void main(String[] args) {

        // Teste - Curso
        validarCurso(1);


    }

    public static void validarCurso(int mode) {
                // Validacao Curso - Adiciona, atualiza, busca e remove curso.
                CursoControle cursoControle = new CursoControle();

                if (mode == 1) {
                    Curso curso = new Curso();
                    curso.setCursoId(3L);
                    //curso.setCursoNome("ADS");
                    //curso.setCursoNome("COMEX");
                    curso.setCursoNome("LOG");

                    try {
                        cursoControle.criarRegistro(curso);
                    } catch (Exception e) {
                        System.out.println("Algo deu errado");
                        System.err.println(e);
                    }
                }

                if (mode == 2) {
                    try {
                        Curso curso = cursoControle.buscarRegistro(1L);
                        System.out.println(curso.getCursoNome());
                    } catch (Exception e) {
                        System.out.println("Algo deu errado");
                        System.err.println(e);
                    }
                }
                
                if (mode == 3) {
                    try {
                        Curso curso = new Curso();
                        curso.setCursoId(1L);
                        curso.setCursoNome("TESTE"); // Mudar de ADS para TESTE
                        cursoControle.atualizarRegistro(curso); //Validar no BD
                    } catch (Exception e) {
                        System.out.println("Algo deu errado");
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
                        System.out.println("Algo deu errado");
                        System.err.println(e);
                    }
                }

                if (mode == 5) {
                    try {
                        Curso curso = new Curso();
                        curso.setCursoId(2L);
                        cursoControle.deletarRegistro(curso);
                    } catch (Exception e) {
                        System.out.println("Algo deu errado");
                        System.err.println(e);
                    }
                }
    }
}