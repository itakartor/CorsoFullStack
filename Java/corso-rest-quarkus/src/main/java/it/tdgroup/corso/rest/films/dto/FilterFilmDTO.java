package it.tdgroup.corso.rest.films.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = FilterFilmDTO.FilterFilmDTOBuilder.class)
@RegisterForReflection
public class FilterFilmDTO {

    private String nome;
    private String genere;
    private RegistaDTO regista;
    private boolean oscar;
    private String attoreNome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FilterFilmDTOBuilder {
    }
}
