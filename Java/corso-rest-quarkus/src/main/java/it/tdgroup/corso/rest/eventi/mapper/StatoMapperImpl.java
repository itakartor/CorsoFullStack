package it.tdgroup.corso.rest.eventi.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.eventi.dto.StatoDTO;
import it.tdgroup.corso.rest.eventi.entity.Stato;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatoMapperImpl extends AbstractMapperComponent<String, Stato> {
    @Override
    public String convertEntityToDto(Stato entity) throws MapperException {
        if(entity != null)
        {
            if(entity == Stato.FINITO)
            {
                return StatoDTO.PUBBLICATO.getDisplayName();
            }
            else
            {
                if(entity == Stato.BOZZA)
                {
                    return StatoDTO.IN_LAVORAZIONE.getDisplayName();
                }
            }
        }
        return null;
    }

    @Override
    public Stato convertDtoToEntity(String dto) throws MapperException {
        if(dto != null)
        {
            if(dto.equals(StatoDTO.IN_LAVORAZIONE.getDisplayName()))
                return Stato.BOZZA;
            else
            {
                if(dto.equals(StatoDTO.PUBBLICATO))
                    return Stato.FINITO;
            }
        }
        return null;
    }
}
