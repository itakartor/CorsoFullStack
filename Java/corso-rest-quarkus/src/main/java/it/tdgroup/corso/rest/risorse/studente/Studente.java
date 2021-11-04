package it.tdgroup.corso.rest.risorse.studente;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@MongoEntity(collection="STUDENTE")
public class Studente {

    private ObjectId id;
    private String nome;
    private String cognome;
    private String matricola;
    private Integer voto;
    private String email;
    private Date dataInserimento;
    private Residenza residenza;
}
