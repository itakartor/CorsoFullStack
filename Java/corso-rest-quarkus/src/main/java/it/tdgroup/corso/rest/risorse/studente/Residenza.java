package it.tdgroup.corso.rest.risorse.studente;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection="RESIDENZA")
public class Residenza {

    private String via;
    private String cap;
    private String provincia;

}
