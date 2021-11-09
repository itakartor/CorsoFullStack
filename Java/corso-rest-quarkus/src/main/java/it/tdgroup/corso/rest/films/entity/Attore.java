package it.tdgroup.corso.rest.films.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import it.tdgroup.corso.rest.films.Sesso;
import lombok.Data;

@Data
@MongoEntity(collection="ATTORE")
public class Attore {

    private String nome;
    private String cognome;
    private Integer eta;
    private Sesso sesso;
}
