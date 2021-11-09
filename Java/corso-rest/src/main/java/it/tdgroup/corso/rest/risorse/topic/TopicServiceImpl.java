package it.tdgroup.corso.rest.risorse.topic;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.topic.post.Post;
import it.tdgroup.corso.rest.risorse.topic.post.utente.Utente;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CommonsLog
@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapperImpl topicMapper;

    @Override
    public String Post(TopicDTO topicDTO) throws MapperException, ServiceException {
        try {
                Topic topic = topicMapper.convertDtoToEntity(topicDTO);
                topic.setDataCreazione(new Date());
                topic.setDataModifica(topic.getDataCreazione());
                topicRepository.save(topic);
                log.info("Ho inserito il topic con id: " + topic.getId());
                return topic.getId().toHexString();
        }
        catch (MapperException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void Delete(String id) throws ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findById(id);
            if (topic.isPresent()) {
                topicRepository.delete(topic.get());
            } else {
                throw new ServiceException("Topic con id:" + id + " non trovata!");
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void Update(String id, TopicDTO topicDTO) throws ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findById(id);
            if (topic.isPresent()) {
                log.info("topic con id:" + id + " trovata sul DB.");
                Topic newtopic = topicMapper.convertDtoToEntity(topicDTO);
                //System.out.println("IDDDDDDDDDDDD" + topic.get().getId());
                newtopic.setId(topic.get().getId());
                newtopic.setDataModifica(new Date()); //aggiorno la data di modifica
                newtopic.setDataCreazione(topic.get().getDataCreazione());
                if(newtopic.getCategoria() == null)
                {
                    newtopic.setCategoria(topicDTO.getCategoria());
                }
                if(newtopic.getTitolo() == null)
                {
                    newtopic.setTitolo(topicDTO.getTitolo());
                }
                //System.out.println(newtopic.getPosts());
                if(newtopic.getPosts().isEmpty()) {//oltre a controllare se gli utenti non ci sono andrebbe controllato se
                    //tutti i campi sono riempiti perchè se no elimina informazioni
                    for (Post i : topic.get().getPosts()) {
                        newtopic.getPosts().add(Post.builder()
                                        .testo(i.getTesto())
                                        .ora(i.getOra())
                                        .utente(Utente.builder()
                                                .nome(i.getUtente().getNome())
                                                .cognome(i.getUtente().getCognome())
                                                .build())
                                        .build());
                    }
                }
                topicRepository.save(newtopic);
                return;
            }
            throw new ServiceException("topic con id:" + id + " non trovato!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<TopicDTO> findAll() {
        try{
            List<Topic> topics = topicRepository.findAll();//predo tutti gli studenti
            if(!topics.isEmpty())
                return topicMapper.convertEntityToDto(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TopicDTO> find(String camp,String value) throws MapperException {
        List<Topic> result = null;

        //System.out.println("CAMPO " + camp +" VALUE " + value);
        switch (camp)
        {
            case"id":
            {
                //System.out.println("sono entrato nel ID");
                if(topicRepository.findById(value).isPresent())
                {
                    result = List.of(topicRepository.findById(value).get());
                    return topicMapper.convertEntityToDto(result);
                }
                else
                    System.out.println("sono vuota");
                break;
            }
            case"categoria":
            {
                result = topicRepository.findByCategoria(value);
                if(!result.isEmpty())
                {
                    return topicMapper.convertEntityToDto(result);
                }
                break;
            }
            case"titolo":
            {
                result = topicRepository.findByTitolo(value);
                if(!result.isEmpty())
                {
                    return topicMapper.convertEntityToDto(result);
                }
                break;
            }
            default://in caso che la richiesta non sia chiara restituisco tutto
            {
                return findAll();
            }
        }
        return null;
    }

    @Override
    public List<TopicDTO> findByTitolo(String Titolo) {
        try{
            List<Topic> topics = topicRepository.findByTitolo(Titolo);//predo tutti gli studenti
            if(!topics.isEmpty())
                return topicMapper.convertEntityToDto(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TopicDTO> findByCategoria(String Categoria) {
        try{
            List<Topic> topics = topicRepository.findByCategoria(Categoria);//predo tutti gli studenti
            if(!topics.isEmpty())
                return topicMapper.convertEntityToDto(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
