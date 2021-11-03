package it.tdgroup.corso.rest.risorse.post;

import it.tdgroup.corso.rest.risorse.utente.Utente;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "POST")
public class Post {
    @Id
    private ObjectId id;
    private String testo;
    private String ora;
    private Utente utente;
}
