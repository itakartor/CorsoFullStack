--CreazioneDB Università
CREATE DATABASE DBProgetti_Vitiello
USE DBProgetti_Vitiello

CREATE TABLE Impiegati(
	Matricola int not null,
	Cognome varchar(255) not null,
	Nome varchar(255) not null,
	Stipendio int,
	CodDipartimento Varchar(2) not NULL,
	CONSTRAINT PK_Impiegati primary key (Matricola)
)

CREATE TABLE Dipartimento(
	Codice Varchar(2) NOT null,
	Denominazione varchar(255) not null,
	Sede varchar(255),
	MatricolaDirettore int not null,
	CONSTRAINT PK_Dipartimento primary key(Codice)
)

CREATE TABLE Progetti(
	Sigla varchar(255) not null,
	NomeProgetto varchar(255) not null,
	Bilancio int,
	MatricolaResponsabile int not null,
	CONSTRAINT PK_Progetti primary key (Sigla)
)
CREATE TABLE AssegnazioneProgetti(
	IdAssegnazione int not null identity(1,1),
	MatricolaImpiegato int not null,
	SiglaProgetto varchar(255) not null,
	CONSTRAINT PK_AssegnazioneProgetti primary key (IdAssegnazione)
)



INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (101,'Sili','Marco',60,'NO')
INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (102,'Rossi','Simone',40,'NO')
INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (103,'Neri','Matteo',40,'NO')
INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (201,'Neri','Simone',40,'SU')
INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (202,'Verdi','Osvaldo',50,'SU')
INSERT INTO Impiegati(Matricola,
	Cognome,
	Nome,
	Stipendio,
	CodDipartimento)
VALUES (301,'Bisi','Antonio',70,'IS')


Insert into Dipartimento(
	Codice,
	Denominazione,
	Sede,
	MatricolaDirettore)
VALUES('NO','Nord','Milano',101)
Insert into Dipartimento(
	Codice,
	Denominazione,
	Sede,
	MatricolaDirettore)
VALUES('SU','Sud','Napoli',201)
Insert into Dipartimento(
	Codice,
	Denominazione,
	Sede,
	MatricolaDirettore)
VALUES('IS','Isole','Palermo',301)

Insert into Progetti(
	Sigla,
	NomeProgetto,
	Bilancio,
	MatricolaResponsabile)
VALUES('Alpha','Vendite',30,202)
Insert into Progetti(
	Sigla,
	NomeProgetto,
	Bilancio,
	MatricolaResponsabile)
VALUES('Beta','Inventario',50,301)
Insert into Progetti(
	Sigla,
	NomeProgetto,
	Bilancio,
	MatricolaResponsabile)
VALUES('Gamma','Distribuzione',18,301)


insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(101,'Alpha')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(101,'Beta')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(103,'Alpha')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(103,'Beta')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(201,'Beta')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(202,'Beta')
insert into AssegnazioneProgetti(
	MatricolaImpiegato,
	SiglaProgetto)
VALUES(202,'Gamma')



ALTER TABLE Impiegati
ADD CONSTRAINT FK_Dipartimento FOREIGN KEY (CodDipartimento) REFERENCES Dipartimento(Codice)

ALTER TABLE Dipartimento
ADD CONSTRAINT FK_Impiegati FOREIGN KEY (MatricolaDirettore) REFERENCES Impiegati(Matricola)

Alter Table Progetti
ADD CONSTRAINT FK_Responsabile FOREIGN KEY (MatricolaResponsabile) REFERENCES Impiegati(Matricola)

Alter Table AssegnazioneProgetti
ADD CONSTRAINT FK_Progetti FOREIGN KEY (SiglaProgetto) REFERENCES Progetti(Sigla)
Alter Table AssegnazioneProgetti
ADD CONSTRAINT FK_Collaboratore FOREIGN KEY (MatricolaImpiegato) REFERENCES Impiegati(Matricola)

SELECT Impiegati.cognome,Impiegati.matricola
FROM Impiegati
WHERE Impiegati.stipendio > 50

SELECT Impiegati.cognome,Impiegati.stipendio
FROM Impiegati
	JOIN Dipartimento ON Dipartimento.Codice = Impiegati.CodDipartimento
WHERE Dipartimento.Sede like 'Roma'

SELECT Impiegati.Cognome, Dipartimento.Denominazione
FROM Impiegati
	JOIN Dipartimento ON Dipartimento.Codice = Impiegati.CodDipartimento
	
SELECT Impiegati.Cognome, Dipartimento.Denominazione
FROM Impiegati
	JOIN Dipartimento ON Dipartimento.MatricolaDirettore = Impiegati.Matricola
	
SELECT Impiegati.Cognome,Progetti.NomeProgetto
FROM Progetti
	JOIN Impiegati ON Impiegati.Matricola = Progetti.MatricolaResponsabile

SELECT Impiegati.Cognome,Progetti.NomeProgetto
FROM Progetti
	JOIN AssegnazioneProgetti ON AssegnazioneProgetti.SiglaProgetto = Progetti.Sigla
		JOIN Impiegati ON Impiegati.Matricola = AssegnazioneProgetti.MatricolaImpiegato
WHERE Progetti.Bilancio>40

SELECT a.Cognome
FROM Impiegati a
	JOIN Dipartimento ON Dipartimento.Codice = a.CodDipartimento
WHERE a.Stipendio > (
	SELECT Impiegati.Stipendio
	FROM Impiegati 
		JOIN Dipartimento ON Dipartimento.MatricolaDirettore = Impiegati.Matricola
	WHERE a.CodDipartimento LIKE Impiegati.CodDipartimento	
)

SELECT Distinct a.Cognome
FROM Impiegati a
	JOIN Dipartimento ON Dipartimento.MatricolaDirettore = a.Matricola
	JOIN Progetti ON Progetti.MatricolaResponsabile = a.Matricola
	
SELECT c.Denominazione
FROM Dipartimento c
WHERE c.Codice IN (
	SELECT a.CodDipartimento
	FROM Impiegati a
	WHERE a.CodDipartimento LIKE c.Codice AND a.Stipendio>60
)

SELECT c.Denominazione
FROM Dipartimento c
WHERE c.Codice NOT IN (
	SELECT a.CodDipartimento
	FROM Impiegati a
	WHERE a.CodDipartimento LIKE c.Codice AND a.Stipendio<60
)

SELECT a.Cognome
FROM Impiegati a 
WHERE a.Stipendio IN(
	SELECT MAX(a.Stipendio)
	FROM Impiegati a
)

SELECT a.Cognome,a.Matricola
FROM Impiegati a
WHERE a.Matricola NOT IN (
	SELECT d.MatricolaImpiegato
	FROM AssegnazioneProgetti d
)

SELECT a.Cognome,a.Matricola
FROM Impiegati a
WHERE 1 < (
	SELECT COUNT(d.MatricolaImpiegato)
	FROM AssegnazioneProgetti d
	WHERE d.MatricolaImpiegato LIKE a.Matricola
)

SELECT a.Cognome,a.Matricola
FROM Impiegati a
WHERE 1 = (
	SELECT COUNT(d.MatricolaImpiegato)
	FROM AssegnazioneProgetti d
	WHERE d.MatricolaImpiegato LIKE a.Matricola
)

SELECT AVG(a.Stipendio) AS StipendioMedio, c.Codice
FROM Impiegati a
	JOIN Dipartimento c ON c.Codice = a.CodDipartimento
GROUP BY c.Codice

SELECT a.Matricola, a.Cognome
FROM Impiegati a
WHERE a.Stipendio > (
	SELECT 0.1*AVG(e.Stipendio) + AVG(e.Stipendio) AS StipendioMedio
	FROM Impiegati e
		JOIN Dipartimento d ON d.Codice = e.CodDipartimento
	GROUP BY d.Codice
	Having d.Codice LIKE a.CodDipartimento
)
	