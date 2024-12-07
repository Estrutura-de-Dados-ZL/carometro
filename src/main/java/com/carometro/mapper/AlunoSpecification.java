package com.carometro.mapper;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carometro.model.Aluno;

public class AlunoSpecification implements Specification<Aluno> {

        private final String nome;
        private final String ano;
        private final String semestre;
        private final String curso;

        public AlunoSpecification(String nome, String ano, String semestre, String curso) {
                this.nome = nome;
                this.ano = ano;
                this.semestre = semestre;
                this.curso = curso;
        }

        @Override
        public Predicate toPredicate(Root<Aluno> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                // Filtragem de nome
                Optional.ofNullable(nome)
                                .filter(StringUtils::hasText)
                                .ifPresent(n -> predicates.add(
                                                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
                                                                "%" + n.toLowerCase() + "%")));

                // Filtragem de ano (acessando Turma > ano)
                Optional.ofNullable(ano)
                                .filter(StringUtils::hasText)
                                .ifPresent(a -> predicates.add(criteriaBuilder.like(
                                                criteriaBuilder.lower(root.get("turma").get("id").get("ano")),
                                                "%" + a.toLowerCase() + "%")));

                // Filtragem de semestre (acessando Turma > semestre)
                Optional.ofNullable(semestre)
                                .filter(StringUtils::hasText)
                                .ifPresent(s -> predicates
                                                .add(criteriaBuilder.like(
                                                                criteriaBuilder.lower(root.get("turma").get("id")
                                                                                .get("semestre")),
                                                                "%" + s.toLowerCase() + "%")));

                // Filtragem de curso (acessando Turma > curso > nome)
                Optional.ofNullable(curso)
                                .filter(StringUtils::hasText)
                                .ifPresent(c -> predicates
                                                .add(criteriaBuilder.like(
                                                                criteriaBuilder.lower(root.get("turma").get("curso")
                                                                                .get("nome")),
                                                                "%" + c.toLowerCase() + "%")));

                // Caso nenhum filtro seja aplicado, retorna uma expressão sempre verdadeira
                return predicates.isEmpty() ? criteriaBuilder.conjunction()
                                : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }
}
