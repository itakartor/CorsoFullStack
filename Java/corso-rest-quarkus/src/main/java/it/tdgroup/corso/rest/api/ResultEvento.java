package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.films.dto.FilmDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResultEvento {
     List<EventoDTO> eventoDTOList;
     List<FilmDTO> films;
}
