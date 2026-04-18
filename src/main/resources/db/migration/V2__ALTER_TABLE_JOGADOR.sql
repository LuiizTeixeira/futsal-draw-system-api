ALTER TABLE futsal.jogador
    ADD COLUMN habilidade INT NOT NULL CHECK (habilidade >= 1 AND habilidade <= 5),
    ADD COLUMN numero INT UNIQUE,
    ADD COLUMN presenca CHAR(1) DEFAULT 'N' NOT NULL CHECK (presenca IN ('S', 'N'));
