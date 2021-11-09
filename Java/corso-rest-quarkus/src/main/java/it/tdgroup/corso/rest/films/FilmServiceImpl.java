package it.tdgroup.corso.rest.films;

import io.quarkus.mongodb.panache.PanacheQuery;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.eventi.entity.Evento;
import it.tdgroup.corso.rest.films.dto.FilmDTO;
import it.tdgroup.corso.rest.films.dto.FilterFilmDTO;
import it.tdgroup.corso.rest.films.entity.Film;
import it.tdgroup.corso.rest.films.mapper.FilmMapperImpl;
import it.tdgroup.corso.rest.films.mapper.RegistaMapperImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
@CommonsLog
public class FilmServiceImpl implements FilmService{
    @Inject
    FilmRepository filmRepository;

    @Inject
    FilmMapperImpl filmMapper;

    @Inject
    RegistaMapperImpl registaMapper;

    @Override
    public List<FilmDTO> elenco() throws ServiceException {
        try {
            List<Film> film = filmRepository.listAll();
            return filmMapper.convertEntityToDto(film);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String crea(FilmDTO filmDTO) throws ServiceException {
        try {
            log.info("sono nella crea");
            Film film = filmMapper.convertDtoToEntity(filmDTO);
            filmRepository.persist(film);
            //String id = Film.getId().toString();
            log.info("ID film creato:" + film.getId().toHexString());
            return film.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaFilm(String idFilm, FilmDTO filmDTO) throws ServiceException {
        try {
            Optional<Film> film = filmRepository.findByIdOptional(new ObjectId(idFilm));
            if (film.isPresent()) {
                log.info("Film con id:" + idFilm + " trovato sul DB.");
                if(filmDTO.getDurata() == 0)
                    filmDTO.setDurata(film.get().getDurata());
                if(filmDTO.getGenere() == null)
                    filmDTO.setGenere(film.get().getGenere());
                if(filmDTO.getNome() == null)
                    filmDTO.setNome(film.get().getNome());
                if(filmDTO.getAnno() == 0)
                    filmDTO.setAnno(film.get().getAnno().getYear());
                if(!filmDTO.isOscar())
                    filmDTO.setOscar(film.get().isOscar());
                if(filmDTO.getRegista() == null)
                    filmDTO.setRegista(registaMapper.convertEntityToDto(film.get().getRegista()));

                Film newFilm = filmMapper.convertDtoToEntity(filmDTO);
                newFilm.setId(film.get().getId());

                filmRepository.update(newFilm);
                return;
            }
            throw new ServiceException("Film con id:" + idFilm + " non trovato!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Film> film = filmRepository.findByIdOptional(new ObjectId(id));
            if (film.isPresent()) {
                filmRepository.delete(film.get());
            } else {
                throw new ServiceException("Film con id:" + id + " non trovato!");
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<FilmDTO> find(FilterFilmDTO filterDTO) throws ServiceException {
        try {
            PanacheQuery<Film> films = filmRepository.find(filterDTO);
            if (!films.list().isEmpty()) {
                return filmMapper.convertEntityToDto(films.list());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
