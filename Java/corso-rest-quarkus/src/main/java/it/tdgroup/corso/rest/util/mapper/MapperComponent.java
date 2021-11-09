package it.tdgroup.corso.rest.util.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;

import java.text.ParseException;
import java.util.List;


public interface MapperComponent<T, E> {

    T convertEntityToDto(E entity) throws MapperException;

    E convertDtoToEntity(T dto) throws MapperException, ParseException;
    
    List<T> convertEntityToDto(List<E> entityList) throws MapperException;

    List<E> convertDtoToEntity(List<T> dtoList) throws MapperException, ParseException;

}
