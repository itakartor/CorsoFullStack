es1

CREATE DATABASE DBFILM_Vitiello
USE DBFILM_Vitiello

CREATE TABLE Attori(
	IdAttori int Not NULL,
	Nominativo Varchar(255) NOT NULL,
	AnnoNascita int ,
	Nazionalita VARCHAR(255),
	CONSTRAINT PK_Attori PRIMARY KEY (IdAttori)
)

CREATE TABLE Recitazione(
	IdRecitazione int NOT NULL,
	IdAttori int Not NULL,
	IdFilm int NOT NULL,
	Ruolo VARCHAR(255),
	CONSTRAINT PK_Recitazione PRIMARY KEY (IdRecitazione)
)

CREATE TABLE Film(
	IdFilm INT NOT NULL,
	Titolo VARCHAR NOT NULL,
	AnnoProduzione int,
	Nazionalita VARCHAR(255),
	Regista VARCHAR(255) NOT NULL,
	Genere VARCHAR(255),	
	CONSTRAINT PK_Film PRIMARY KEY (IdFilm)
)


CREATE TABLE Proiezione(
	IdProiezione int NOT NULL,
	IdFilm INT NOT NULL,
	IdSala INT NOT NULL,	
	CONSTRAINT PK_Proiezione PRIMARY KEY (IdProiezione)
)

CREATE TABLE Sale(
	IdSala int not null,
	Nome varchar(255) not null,
	Posti int DEFAULT '1',
	Citta VARCHAR(255),	
	CONSTRAINT PK_Sale PRIMARY KEY (IdSala)
)

ALTER TABLE Recitazione
add CONSTRAINT FK_Attori FOREIGN KEY(IdAttori) REFERENCES Attori(IdAttori)
ALTER TABLE Recitazione
add CONSTRAINT FK_Film FOREIGN KEY(IdFilm) REFERENCES Film(IdFilm)

ALTER TABLE Proiezioneint
add CONSTRAINT FK_Film2 FOREIGN KEY(IdFilm) REFERENCES Film(IdFilm)
ALTER TABLE Proiezione
add CONSTRAINT FK_Sale FOREIGN KEY(IdSala) REFERENCES Sale(IdSala)



CREATE DATABASE DBSinistri_Vitiello
USE DBSinistri_Vitiello

CREATE TABLE Sinistri(
	CodSinistro VARCHAR(255) not NULL,
	Data Date not NULL,
	Luogo VARCHAR(255) not null,
	CONSTRAINT PK_Sinistri PRIMARY KEY (CodSinistro) 
)
CREATE TABLE AutoCoinvolte(
	IdAutoCoinvolte int not null,
	CodSinistro VARCHAR(255) not null,
	Targa VARCHAR(28) not null,
	CONSTRAINT Fk_AutoCoinvolte PRIMARY KEY (IdAutoCoinvolte)
)
CREATE TABLE Auto(
	Targa VARCHAR(28) not NULL,
	Marca varchar(255),
	Cilindrata int,
	Potenza DECIMAL,
	CF VARCHAR(16) not null,
	CodiceAssicurazione VARCHAR(255) not null,
	CONSTRAINT PK_Auto primary key (Targa)
)
CREATE TABLE Proprietario(
	CF VARCHAR(16) not null,
	Nominativo Varchar(255) NOT NULL,
	Residenza VARCHAR(255),
	CONSTRAINT PK_Proprietario primary key (CF)
)
CREATE TABLE Assicurazione(
	CodiceAssicurazione VARCHAR(255) not null,
	Nome varchar(255) not null,
	Sede varchar(255),
	CONSTRAINT PK_assicurazione primary key (CodiceAssicurazione)
)

ALTER TABLE AutoCoinvolte
add CONSTRAINT FK_CodSinistro FOREIGN KEY(CodSinistro) REFERENCES Sinistri(CodSinistro)
ALTER TABLE AutoCoinvolte
add CONSTRAINT FK_Targa FOREIGN KEY(Targa) REFERENCES Auto(Targa)

ALTER TABLE Auto
add CONSTRAINT FK_CF FOREIGN KEY(CF) REFERENCES Proprietario(CF)
ALTER TABLE Auto
add CONSTRAINT FK_Assicurazioni FOREIGN KEY(CodiceAssicurazione) REFERENCES Assicurazione(CodiceAssicurazione)



Create DATABASE DBCorsiLaurea_Vitiello
Use DBCorsiLaurea_Vitiello

Create TABLE Studenti(
	Matricola int not null,
	Nominativo VARCHAR not null,
	AnnoNascita int,
	CONSTRAINT PK_Studenti PRIMARY key (Matricola)
)
Create TABLE Frequenta(
	IdFrequenta int not null,
	Matricola int not null,
	CodCorso int not null,
	CONSTRAINT PK_Frequenta PRIMARY key (IdFrequenta)
)
Create TABLE Docenti(
	CodDocente int not null,
	Nominativo VARCHAR(255) not null,
	Dipartimento VARCHAR(255),
	CONSTRAINT PK_Docenti primary key (CodDocente)
)
Create TABLE Corsi(
	CodCorso int not null,
	NomeCorso VARCHAR(255) not null,
	CodDocente int not null,
	CorsoLaurea int not null,
	CONSTRAINT PK_Corsi primary key (CodCorso)
)
Create TABLE CorsoLaurea(
	CodCorsoLaurea int not null,
	TipoLaurea VARCHAR(255) not null,
	Facolta varchar(255) not null,
	CONSTRAINT PK_CorsoLaurea primary key (CodCorsoLaurea)
)

ALTER TABLE Frequenta 
add CONSTRAINT FK_Matricola FOREIGN KEY(Matricola) REFERENCES Studenti(Matricola)
ALTER TABLE Frequenta
add CONSTRAINT FK_CodCorso FOREIGN KEY(CodCorso) REFERENCES Corsi(CodCorso)

ALTER TABLE Corsi
add CONSTRAINT FK_CodDocente FOREIGN KEY(CodDocente) REFERENCES Docenti(CodDocente)
ALTER TABLE Corsi
add CONSTRAINT FK_CorsoLaurea FOREIGN KEY(CorsoLaurea) REFERENCES CorsoLaurea(CodCorsoLaurea)

