package ness.service;

import ness.model.Message;
import ness.repository.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageCrudRepository repository;

    @Autowired
    public MessageServiceImpl(MessageCrudRepository messageCrudRepository){
        this.repository = messageCrudRepository;
    }

    @Override
    public int save(Message message) {
        if(repository.save(message) != null)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "MessageServiceImpl{" +
                "repository=" + repository +
                '}';
    }
}
