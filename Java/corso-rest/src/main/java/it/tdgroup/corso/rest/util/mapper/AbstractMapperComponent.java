package it.tdgroup.corso.rest.util.mapper;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.risorse.topic.Topic;
import it.tdgroup.corso.rest.risorse.topic.TopicDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by s.g.
 */
public abstract class AbstractMapperComponent<T, E> implements MapperComponent<T, E> {

    @Override
    public List<T> convertEntityToDto(List<E> entityList) throws MapperException {
        List<T> dtoList = new ArrayList<>();
        if (entityList != null) {
            for (E entity : entityList) {
                T dto = convertEntityToDto(entity);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @Override
    public List<E> convertDtoToEntity(List<T> dtoList) throws MapperException {
        List<E> entityList = new ArrayList<>();
        if (dtoList != null) {
            for (T dto : dtoList) {
                E entity = convertDtoToEntity(dto);
                entityList.add(entity);
            }
        }
        return entityList;
    }
}
