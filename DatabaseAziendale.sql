-- Creazione DB
CREATE DATABASE gestionale;
-- Utilizzo il DB appena creato
USE gestionale;
-- Creo la tabella per il dipendente
CREATE TABLE dipendente(
    idDipendente INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    nome VARCHAR(255),
    cognome VARCHAR(255),
    ruolo VARCHAR(255),
    livello INT, -- andrà ad occupare interi 0 1 2 3
    stipendio FLOAT,
    sede VARCHAR(255),
    anniServizio INT
);

-- Creo la tabella dell'orario settimanale
CREATE TABLE orariLavoratori(
    idOrario INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    idDipendente INT NOT NULL,
    giornoSettimana VARCHAR(32),
    orario1 INT, -- ci sarà l'orario 9-13
    orario2 INT, -- ci sarà l'orario 14-18
    orario3 INT, -- ci sarà l'orario 18-22
    FOREIGN KEY (idDipendente) REFERENCES dipendente(idDipendente) ON DELETE CASCADE
);

INSERT INTO dipendente (nome, cognome, ruolo, livello, stipendio, sede, anniServizio)
VALUES ('Mario', 'Rossi', 'Sviluppatore', 1, 1500.00, 'Milano', 3),
       ('Laura', 'Bianchi', 'Project Manager', 3, 1800.00, 'Roma', 5),
       ('Giovanni', 'Verdi', 'Analista', 1, 1500.00, 'Napoli', 1);
       
INSERT INTO orariLavoratori (idDipendente, giornoSettimana, orario1, orario2, orario3)
VALUES (1, 'Lunedì', 4, 0, 4),
       (1, 'Martedì', 4, 4, 0),
       (1, 'Mercoledì', 0, 4, 4),
       (1, 'Giovedì', 0, 4, 0),
       (1, 'Venerdì', 4, 0, 4),
       (2, 'Lunedì', 4, 0, 4),
       (2, 'Martedì', 4, 4, 0),
       (2, 'Mercoledì', 4, 0, 4),
       (2, 'Giovedì', 4, 4, 0),
       (2, 'Venerdì', 4, 4, 0),
       (3, 'Lunedì', 0, 4, 4),
       (3, 'Martedì', 4, 4, 0),
       (3, 'Mercoledì', 0, 4, 4),
       (3, 'Giovedì', 0,4, 4),
       (3, 'Venerdì', 4, 4, 4);

       
       