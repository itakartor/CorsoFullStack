package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.risorse.topic.TopicDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResultTopic {
    List<TopicDTO> Topics;
}
