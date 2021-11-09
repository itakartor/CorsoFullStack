package it.tdgroup.corso.rest.risorse.topic.post;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.risorse.topic.post.utente.UtenteDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = PostDTO.PostDTOBuilder.class)
public class PostDTO {

    private String testo;
    private String ora;
    private UtenteDTO utente;
    @JsonPOJOBuilder(withPrefix = "")
    public static class PostDTOBuilder {
    }

}
