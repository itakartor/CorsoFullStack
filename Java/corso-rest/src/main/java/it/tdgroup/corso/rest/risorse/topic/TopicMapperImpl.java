package it.tdgroup.corso.rest.risorse.topic;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.risorse.topic.post.PostMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMapperImpl extends AbstractMapperComponent<TopicDTO,Topic> {

    @Autowired
    PostMapperImpl postMapper;

    @Override
    public TopicDTO convertEntityToDto(Topic entity) throws MapperException {
        if(entity != null)
        {
            TopicDTO topicDTO = TopicDTO.builder()
                    .categoria(entity.getCategoria())
                    .titolo(entity.getTitolo())
                    .posts(postMapper.convertEntityToDto(entity.getPosts()))
                    .build();
            return topicDTO;
        }
        return null;
    }

    @Override
    public Topic convertDtoToEntity(TopicDTO dto) throws MapperException {
        if(dto != null)
        {
            Topic topic = Topic.builder()
                    .categoria(dto.getCategoria())
                    .titolo(dto.getTitolo())
                    .posts(postMapper.convertDtoToEntity(dto.getPosts()))
                    .build();
            return topic;
        }
        return null;
    }
}
