package it.tdgroup.corso.rest.films.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@MongoEntity(collection="FILM")
public class Film {

    private ObjectId id;
    private String nome;
    private String genere;
    private Integer durata;
    private Date anno;
    private Regista regista;
    private boolean oscar;
    private List<Attore> attori;
}
