package it.tdgroup.corso.rest.films.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.films.Sesso;
import it.tdgroup.corso.rest.films.dto.RegistaDTO;
import it.tdgroup.corso.rest.films.entity.Regista;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import java.text.ParseException;

@ApplicationScoped
public class RegistaMapperImpl extends AbstractMapperComponent<RegistaDTO, Regista> {

    @Override
    public RegistaDTO convertEntityToDto(Regista entity) throws MapperException {
        if(entity != null)
        {
            return RegistaDTO.builder()
                    .nome(entity.getNome())
                    .eta(entity.getEta())
                    .cognome(entity.getCognome())
                    .sesso(entity.getSesso().getGenere())
                    .build();
        }
        return null;
    }

    @Override
    public Regista convertDtoToEntity(RegistaDTO dto) throws MapperException, ParseException {
        if(dto != null)
        {
            Regista regista = new Regista();
            regista.setSesso(Sesso.controllo(dto.getSesso()));
            regista.setNome(dto.getNome());
            regista.setEta(dto.getEta());
            regista.setCognome(dto.getCognome());
            return regista;
        }
        return null;
    }
}
