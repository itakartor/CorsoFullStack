package it.tdgroup.corso.rest.eventi.utente;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtenteMapperImpl extends AbstractMapperComponent<UtenteDTO,Utente> {
    @Override
    public UtenteDTO convertEntityToDto(Utente entity) throws MapperException {
        if(entity != null)
        {
            return UtenteDTO.builder()
                    .cognome(entity.getCognome())
                    .nome(entity.getNome())
                    .build();
        }
        return null;
    }

    @Override
    public Utente convertDtoToEntity(UtenteDTO dto) throws MapperException {
        if(dto != null)
        {
            Utente utente = new Utente();
            utente.setCognome(dto.getCognome());
            utente.setNome(dto.getNome());
            return utente;
        }
        return null;
    }
}
