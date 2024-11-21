package org.example.bankingportal.controller;

import org.example.bankingportal.entities.Messages;
import org.example.bankingportal.repositories.MessagesLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final MessagesLab messageLab;

    @Autowired
    public MessageController(MessagesLab messagesLab) {
        this.messageLab = messagesLab;
    }

    @GetMapping("/messages")
    public List<Messages> getMessages() {
        return messageLab.getMessages();
    }

    @PostMapping("/createMessages")
    public ResponseEntity<Messages> createMessage(@RequestBody Messages message) {
        messageLab.createMessage(message);
        return ResponseEntity.ok().build();
    }


}
