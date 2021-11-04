package it.tdgroup.corso.rest.risorse.studente;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = FilterDTO.FilterDTOBuilder.class)
@RegisterForReflection
public class FilterDTO {

    private String nome;
    private String cognome;
    private String matricola;
    private Integer voto;
    private String email;
    private ResidenzaDTO residenza;
    @JsonPOJOBuilder(withPrefix = "")
    public static class FilterDTOBuilder {
    }
}
