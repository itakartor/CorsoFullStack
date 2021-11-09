package it.tdgroup.corso.rest.risorse.topic.post;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.risorse.topic.post.utente.UtenteMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl extends AbstractMapperComponent<PostDTO,Post> {

    @Autowired
    UtenteMapperImpl utenteMapper;

    @Override
    public PostDTO convertEntityToDto(Post entity) throws MapperException {
        if(entity != null)
        {
            PostDTO postDTO = PostDTO.builder()
                    .utente(utenteMapper.convertEntityToDto(entity.getUtente()))
                    .ora(entity.getOra())
                    .testo(entity.getTesto())
                    .build();
            return postDTO;
        }
        return null;
    }

    @Override
    public Post convertDtoToEntity(PostDTO dto) throws MapperException {
        if(dto != null)
        {
            Post post = Post.builder()
                    .ora(dto.getOra())
                    .utente(utenteMapper.convertDtoToEntity(dto.getUtente()))
                    .testo(dto.getTesto())
                    .build();
            return post;
        }
        return null;
    }
}
