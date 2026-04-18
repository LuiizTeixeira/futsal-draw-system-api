ALTER TABLE futsal.jogador ADD COLUMN habilidade INT NOT NULL CHECK (habilidade >= 1 AND habilidade <= 5);
ALTER TABLE futsal.jogador ADD COLUMN numero INT UNIQUE;
ALTER TABLE futsal.jogador ADD COLUMN presenca CHAR(1) DEFAULT 'N' NOT NULL CHECK (presenca IN ('S', 'N'));
