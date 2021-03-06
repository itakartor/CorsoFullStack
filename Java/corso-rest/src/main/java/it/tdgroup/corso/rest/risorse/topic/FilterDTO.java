package it.tdgroup.corso.rest.risorse.topic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.risorse.topic.post.PostDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = FilterDTO.FilterDTOBuilder.class)
public class FilterDTO {
    private String titolo;
    private String categoria;
    List<PostDTO> posts;
    @JsonPOJOBuilder(withPrefix = "")
    public static class FilterDTOBuilder {
    }
}
