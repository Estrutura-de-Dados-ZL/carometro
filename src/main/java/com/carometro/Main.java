package com.carometro;

import com.carometro.controller.CursoControle;
import com.carometro.model.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Curso c = new Curso();
        CursoControle controle = new CursoControle();

        c.setCursoNome("ADS");
        try {
            controle.criarRegistro(c);

        } catch (Exception e) {
            System.out.println("quebrou");
        }
    }
}