package it.tdgroup.corso.rest.risorse.topic;

import it.tdgroup.corso.rest.risorse.topic.post.Post;
import it.tdgroup.corso.rest.risorse.topic.post.PostDTO;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
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
    private List<Post> posts;
    private Date dataCreazione;
    private Date dataModifica;
}
