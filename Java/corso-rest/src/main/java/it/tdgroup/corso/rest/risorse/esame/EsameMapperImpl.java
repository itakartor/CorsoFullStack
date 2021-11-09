package it.tdgroup.corso.rest.risorse.esame;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.risorse.esame.docente.DocenteMapperImpl;
import it.tdgroup.corso.rest.studente.StudenteMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EsameMapperImpl extends AbstractMapperComponent<EsameDTO,Esame> {

    @Autowired
    DocenteMapperImpl docenteMapper;

    @Autowired
    StudenteMapperImpl studenteMapper;

    @Override
    public EsameDTO convertEntityToDto(Esame entity) throws MapperException {
        if(entity != null)
        {
            EsameDTO esameDTO = EsameDTO.builder()
                    .denominazione(entity.getDenominazione())
                    .docente(docenteMapper.convertEntityToDto(entity.getDocente()))
                    .studenti(studenteMapper.convertEntityToDto(entity.getStudenti()))
                    .build();
            return esameDTO;
        }
        return null;
    }

    @Override
    public Esame convertDtoToEntity(EsameDTO dto) throws MapperException {
        if(dto != null)
        {
            Esame esame = Esame.builder()
                    .denominazione(dto.getDenominazione())
                    .docente(docenteMapper.convertDtoToEntity(dto.getDocente()))
                    .studenti(studenteMapper.convertDtoToEntity(dto.getStudenti()))
                    .build();
            return esame;
        }
        return null;
    }
}
