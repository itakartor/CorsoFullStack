package it.tdgroup.corso.rest.eventi.partecipante;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection="PARTECIPANTE")
public class Partecipante {
    private String nome;
    private String cognome;
    private String email;
    private String numCell;
    private Integer quota;
}
