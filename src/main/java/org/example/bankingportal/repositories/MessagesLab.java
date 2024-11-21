package org.example.bankingportal.repositories;

import jakarta.annotation.PostConstruct;
import org.example.bankingportal.entities.Messages;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessagesLab {
    private final static AtomicLong counter = new AtomicLong(0L);
    private final static List<Messages> listOfMessage = new ArrayList<>();

    @PostConstruct
    void init() {
        for (Messages message : getDefaultMessage()) {
            message.setId(counter.incrementAndGet());
            listOfMessage.add(message);
        }
    }

    public List<Messages> getMessages() {
        return listOfMessage;
    }

    public Messages createMessage(Messages message) {
        message.setId(counter.incrementAndGet());
        message.setCreatedAt(Instant.now());
        listOfMessage.add(message);
        return message;
    }
    public List<Messages> getDefaultMessage() {
        List<Messages> newMessages = new ArrayList<>();
        newMessages.add(new Messages(null, null, null, null));
        newMessages.add(new Messages(null, null, null, null));

        return newMessages;
    }
}
