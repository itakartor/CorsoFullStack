package it.tdgroup.corso.rest.risorse.topic.post.utente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapperImpl extends AbstractMapperComponent<UtenteDTO,Utente> {


    @Override
    public UtenteDTO convertEntityToDto(Utente entity) throws MapperException {
        if(entity != null)
        {
            UtenteDTO utenteDTO = UtenteDTO.builder()
                    .cognome(entity.getCognome())
                    .nome(entity.getNome())
                    .build();
            return utenteDTO;
        }
        return null;
    }

    @Override
    public Utente convertDtoToEntity(UtenteDTO dto) throws MapperException {
        if(dto != null)
        {
            Utente utente = Utente.builder()
                    .cognome(dto.getCognome())
                    .nome(dto.getNome())
                    .build();
            return utente;
        }
        return null;
    }
}
