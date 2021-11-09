package it.tdgroup.corso.rest.risorse.esame.docente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapperImpl extends AbstractMapperComponent<DocenteDTO,Docente> {
    @Override
    public DocenteDTO convertEntityToDto(Docente entity) throws MapperException {
        if(entity != null)
        {
            DocenteDTO docenteDTO = DocenteDTO.builder()
                    .cognome(entity.getCognome())
                    .email(entity.getEmail())
                    .nome(entity.getNome())
                    .build();
            return  docenteDTO;
        }
        return null;
    }

    @Override
    public Docente convertDtoToEntity(DocenteDTO dto) throws MapperException {
        if(dto != null)
        {
            Docente docente = Docente.builder()
                    .cognome(dto.getCognome())
                    .email(dto.getEmail())
                    .nome(dto.getNome())
                    .build();
            return docente;
        }
        return null;
    }
}
