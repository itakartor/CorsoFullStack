package it.tdgroup.corso.rest.eventi.utente;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection="UTENTE")
public class Utente {
    private String nome;
    private String cognome;
}
