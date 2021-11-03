package it.tdgroup.corso.rest.risorse.topic;

import it.tdgroup.corso.rest.risorse.post.Post;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "TOPIC")
public class Topic {

    @Id
    private ObjectId id;
    private String titolo;
    private String categoria;
    List<Post> posts;
    Date dataCreazione;
    Date dataModifica;
}
