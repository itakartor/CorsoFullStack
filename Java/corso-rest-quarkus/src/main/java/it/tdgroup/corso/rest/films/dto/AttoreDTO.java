package it.tdgroup.corso.rest.films.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = AttoreDTO.AttoreDTOBuilder.class)
@RegisterForReflection
public class AttoreDTO {

    private String nome;
    private String cognome;
    private Integer eta;
    private String sesso; //maschio oppure femmina

    @JsonPOJOBuilder(withPrefix = "")
    public static class AttoreDTOBuilder {
    }
}
