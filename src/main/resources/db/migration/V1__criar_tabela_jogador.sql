CREATE TABLE jogador
(
    id   serial PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL
);