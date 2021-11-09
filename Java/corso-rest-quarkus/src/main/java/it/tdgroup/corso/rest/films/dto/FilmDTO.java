package it.tdgroup.corso.rest.films.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = FilmDTO.FilmDTOBuilder.class)
@RegisterForReflection
public class FilmDTO {

    private String nome;
    private String genere;
    private Integer durata;
    private Integer anno;
    private RegistaDTO regista;
    private boolean oscar;
    private List<AttoreDTO> attori;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FilmDTOBuilder {
    }
}
