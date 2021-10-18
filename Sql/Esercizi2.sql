Create DATABASE DBVideonoleggi_Vitiello
use DBVideonoleggi_Vitiello

Create TABLE Film(
	IdFilm int not null,
	Nome Varchar(255) not null,
	Genere Varchar(255),
	Disponibile Bit not NULL Default 0 ,
	CONSTRAINT PK_Film primary key (IdFilm)
)
Create TABLE Utenti(
	IdUtente int not null,
	Nome Varchar(255) not null,
	eta int default 0,
	CONSTRAINT PK_Utenti PRIMARY key (IdUtente)
)
Create TABLE Noleggi(
	IdNoleggio int not NULL,
	IdUtente int not Null,
	IdFilm int not NULL,
	DataNoleggio DATE not null,
	NumeroGiorni int not null DEFAULT 30,
	Importo DECIMAL not null,
	CONSTRAINT PK_Noleggi primary key (IdNoleggio)
)

ALTER TABLE Noleggi
add CONSTRAINT FK_Utenti FOREIGN KEY (IdUtente) REFERENCES Utenti(IdUtente)
ALTER TABLE Noleggi
add CONSTRAINT FK_Film FOREIGN key (IdFilm) REFERENCES Film(IdFilm)