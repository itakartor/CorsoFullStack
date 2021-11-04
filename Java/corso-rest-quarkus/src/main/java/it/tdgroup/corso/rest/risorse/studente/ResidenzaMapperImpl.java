package it.tdgroup.corso.rest.risorse.studente;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResidenzaMapperImpl extends AbstractMapperComponent<ResidenzaDTO,Residenza> {
    @Override
    public ResidenzaDTO convertEntityToDto(Residenza entity) throws MapperException {
        if(entity != null)
        {
            ResidenzaDTO residenzaDTO = ResidenzaDTO.builder()
                    .cap(entity.getCap())
                    .provincia(entity.getProvincia())
                    .via(entity.getVia())
                    .build();
            return residenzaDTO;
        }
        return null;
    }

    @Override
    public Residenza convertDtoToEntity(ResidenzaDTO dto) throws MapperException {
        if(dto != null)
        {
            Residenza residenza = new Residenza();
                    residenza.setCap(dto.getCap());
                    residenza.setVia(dto.getVia());
                    residenza.setProvincia(dto.getProvincia());
            return residenza;
        }
        return null;
    }

}
