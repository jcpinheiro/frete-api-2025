CREATE TABLE usuario (
    id INTEGER NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    roles JSON NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);