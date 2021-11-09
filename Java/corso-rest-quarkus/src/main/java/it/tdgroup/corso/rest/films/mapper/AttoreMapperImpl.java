package it.tdgroup.corso.rest.films.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.films.Sesso;
import it.tdgroup.corso.rest.films.dto.AttoreDTO;
import it.tdgroup.corso.rest.films.entity.Attore;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import java.text.ParseException;

@ApplicationScoped
public class AttoreMapperImpl extends AbstractMapperComponent<AttoreDTO, Attore> {

    @Override
    public AttoreDTO convertEntityToDto(Attore entity) throws MapperException {
        if(entity != null)
        {
            return AttoreDTO.builder()
                    .nome(entity.getNome())
                    .eta(entity.getEta())
                    .cognome(entity.getCognome())
                    .sesso(entity.getSesso().getGenere())
                    .build();
        }
        return null;
    }

    @Override
    public Attore convertDtoToEntity(AttoreDTO dto) throws MapperException, ParseException {
        if(dto != null)
        {
            Attore attore = new Attore();
            attore.setSesso(Sesso.controllo(dto.getSesso()));
            attore.setNome(dto.getNome());
            attore.setEta(dto.getEta());
            attore.setCognome(dto.getCognome());
            return attore;
        }
        return null;
    }
}
