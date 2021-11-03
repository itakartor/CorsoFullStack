package it.tdgroup.corso.rest.risorse.topic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.risorse.post.PostDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = TopicDTO.TopicDTOBuilder.class)
public class TopicDTO {

    private String titolo;
    private String categoria;
    List<PostDTO> posts;
    @JsonPOJOBuilder(withPrefix = "")
    public static class TopicDTOBuilder {
    }
}
