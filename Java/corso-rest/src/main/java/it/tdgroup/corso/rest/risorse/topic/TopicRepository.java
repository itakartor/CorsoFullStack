package it.tdgroup.corso.rest.risorse.topic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "topicRepository")
public interface TopicRepository extends MongoRepository<Topic,String> {

    List<Topic> findByTitolo(String titolo);

    List<Topic> findByCategoria(String categoria);

}
