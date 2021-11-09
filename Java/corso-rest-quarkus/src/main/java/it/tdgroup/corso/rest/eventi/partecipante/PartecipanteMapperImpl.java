package it.tdgroup.corso.rest.eventi.partecipante;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PartecipanteMapperImpl extends AbstractMapperComponent<PartecipanteDTO,Partecipante> {
    @Override
    public PartecipanteDTO convertEntityToDto(Partecipante entity) throws MapperException {
        if(entity != null)
        {
            return PartecipanteDTO.builder()
                    .cognome(entity.getCognome())
                    .email(entity.getEmail())
                    .nome(entity.getNome())
                    .numCell(entity.getNumCell())
                    .quota(entity.getQuota())
                    .build();
        }
        return null;
    }

    @Override
    public Partecipante convertDtoToEntity(PartecipanteDTO dto) throws MapperException {
        if(dto != null)
        {
            Partecipante partecipante = new Partecipante();
            partecipante.setCognome(dto.getCognome());
            partecipante.setNome(dto.getNome());
            partecipante.setQuota(dto.getQuota());
            partecipante.setEmail(dto.getEmail());
            partecipante.setNumCell(dto.getNumCell());
            return partecipante;
        }
        return null;
    }
}
