package it.tdgroup.corso.rest.films;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.films.dto.FilmDTO;
import it.tdgroup.corso.rest.films.dto.FilterFilmDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface FilmService {
    List<FilmDTO> elenco() throws ServiceException;

    String crea(FilmDTO filmDTO) throws ServiceException;

    void aggiornaFilm(String idFilm, FilmDTO filmDTO) throws ServiceException;

    void cancella(String id) throws ServiceException;

    List<FilmDTO> find(FilterFilmDTO filterDTO) throws ServiceException;
}
