package it.tdgroup.corso.rest.films.mapper;


import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.films.dto.FilmDTO;
import it.tdgroup.corso.rest.films.entity.Film;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@ApplicationScoped
public class FilmMapperImpl extends AbstractMapperComponent<FilmDTO, Film> {

    @Inject
    AttoreMapperImpl attoreMapper;

    @Inject
    RegistaMapperImpl registaMapper;

    @Override
    public FilmDTO convertEntityToDto(Film entity) throws MapperException {
        if(entity != null)
        {
            //System.out.println(entity.toString());
            return FilmDTO.builder()
                    .anno(entity.getAnno().getYear())
                    .attori(attoreMapper.convertEntityToDto(entity.getAttori()))
                    .durata(entity.getDurata())
                    .genere(entity.getGenere())
                    .nome(entity.getNome())
                    .oscar(entity.isOscar())
                    .regista(registaMapper.convertEntityToDto(entity.getRegista()))
                    .build();
        }
        return null;
    }

    @Override
    public Film convertDtoToEntity(FilmDTO dto) throws MapperException, ParseException {
        if(dto != null)
        {
            //System.out.println(dto.toString());
            Film film = new Film();
            film.setAnno(Date.from(ZonedDateTime.of(dto.getAnno(),1,1,12,1,1,1, ZoneId.systemDefault()).toInstant()));
            film.setNome(dto.getNome());
            film.setAttori(attoreMapper.convertDtoToEntity(dto.getAttori()));
            film.setDurata(dto.getDurata());
            film.setGenere(dto.getGenere());
            film.setOscar(dto.isOscar());
            film.setRegista(registaMapper.convertDtoToEntity(dto.getRegista()));
            return film;
        }
        return null;
    }
}
