-- -- Verifica se a tabela 'role' está vazia e insere os roles
-- INSERT INTO role (nome)
-- SELECT 'ADMIN'
-- WHERE NOT EXISTS (SELECT 1 FROM role WHERE nome = 'ADMIN');

-- INSERT INTO role (nome)
-- SELECT 'USER'
-- WHERE NOT EXISTS (SELECT 1 FROM role WHERE nome = 'USER');

-- -- Verifica se o usuário admin já existe e o insere se não existir
-- INSERT INTO usuario (email, senha)
-- SELECT 'admin@admin.com', '$2a$12$UVcOb9EIaBb1Go5Gt.y74.dYCHhPYgiN3V8B6zAF3.Qdld2a438Ge'
-- WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@admin.com');

-- -- Atribui o role 'ADMIN' ao usuário admin
-- INSERT INTO user_role (user_id, role_id)
-- SELECT u.id, r.id
-- FROM usuario u, role r
-- WHERE u.email = 'admin@admin.com'
-- AND r.nome = 'ADMIN'
-- AND NOT EXISTS (
--     SELECT 1 FROM user_role ur
--     WHERE ur.user_id = u.id AND ur.role_id = r.id
-- );
