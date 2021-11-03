package it.tdgroup.corso.rest.risorse.topic;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;

public interface TopicService {
    String Post(TopicDTO topicDTO) throws MapperException, ServiceException;

    void Delete(String id) throws ServiceException;

    void Update(String id, TopicDTO topicDTO) throws ServiceException;

    List<TopicDTO> findAll();

    List<TopicDTO> find(String camp,String Value) throws MapperException;

    List<TopicDTO> findByTitolo(String Titolo);

    List<TopicDTO> findByCategoria(String Categoria);
}
