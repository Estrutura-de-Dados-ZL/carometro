package com.carometro.mapper;

import org.springframework.data.jpa.domain.Specification;
import com.carometro.model.Aluno;

public class AlunoSpecifications {

    public static Specification<Aluno> nomeEquals(String nome) {
        return (root, query, criteriaBuilder) -> nome == null ? null
                : criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Aluno> anoEquals(String ano) {
        return (root, query, criteriaBuilder) -> ano == null ? null
                : criteriaBuilder.equal(root.get("ano"), ano);
    }

    public static Specification<Aluno> semestreEquals(String semestre) {
        return (root, query, criteriaBuilder) -> semestre == null ? null
                : criteriaBuilder.equal(root.get("semestre"), semestre);
    }

    public static Specification<Aluno> cursoEquals(String curso) {
        return (root, query, criteriaBuilder) -> curso == null ? null
                : criteriaBuilder.equal(root.get("curso"), curso);
    }

    public static Specification<Aluno> usuario(String usuario) {
        return (root, query, criteriaBuilder) -> usuario == null ? null
                : criteriaBuilder.equal(root.get("usuario"), usuario);
    }

}
