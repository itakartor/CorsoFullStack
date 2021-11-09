package it.tdgroup.corso.rest.risorse.topic.post.utente;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "UTENTE")
public class Utente {
    @Id
    private ObjectId id;
    private String nome;
    private String cognome;

}
