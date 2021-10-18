Create Database DBAutoofficina_Vitiello
Use DBAutoofficina_Vitiello

Create Table Auto(
	Targa Varchar(28) not null,
	CF VARCHAR(16) not null,
	AnnoImmatricolazione int not null,
	Costruttore varchar(255),
	Modello varchar(255),
	Cilindrata int,
	InformazioniExstra VARCHAR(255),
	Epoca bit not null DEFAULT 0,
	CONSTRAINT PK_Auto primary key (Targa)
)
Create Table Proprietari(
	CF varchar(16) not null,
	nome varchar(255) not null,
	indirizzo varchar(255),
	CONSTRAINT PK_Proprietari primary key (CF)
)
Create Table Interventi(
	IdIntervento int not null,
	Targa Varchar(28) not null,
	CF VARCHAR(16) not null,
	CostoManodopera int not null DEFAULT 0,
	CostoComplessivo int not null DEFAULT 0,
	ServonoPezzi bit not NULL DEFAULT 0,
	CONSTRAINT PK_Interventi primary key (IdIntervento)
)
Create Table Ricambi(
	IdPezzo int not null,
	IdIntervento int not NULL,
	Denominazione varchar(255) not null,
	Costo int DEFAULT 0,
	CONSTRAINT PK_Ricambi primary key (IdPezzo)
)

Create table TipiAuto(
	IdTipo int not null,
	Tipo VARCHAR(255),
	IdPezzo int not null,
	Targa varchar(28) not null,
	CONSTRAINT PK_TipiAuto primary key (IdTipo)
)

Alter table Ricambi
add CONSTRAINT FK_Interventi FOREIGN key (IdIntervento) REFERENCES Interventi(IdIntervento)

Alter table Interventi
add CONSTRAINT FK_Auto FOREIGN key (Targa) REFERENCES Auto(Targa)
Alter table Interventi
add CONSTRAINT FK_Proprietari FOREIGN key (CF) REFERENCES Proprietari(CF) 

Alter table Auto
add CONSTRAINT FK_CF FOREIGN key (CF) REFERENCES Proprietari(CF) 

Alter table TipiAuto
add CONSTRAINT FK_Ricambi FOREIGN key (IdPezzo) REFERENCES Ricambi(IdPezzo)
Alter table TipiAuto
add CONSTRAINT FK_Auto FOREIGN key (Targa) REFERENCES Auto(Targa)
 
