package ru.denis.postservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.denis.common.dto.AuthorEvent;
import ru.denis.postservice.models.AvailableAuthor;

@Service
public class AuthorEventListener {

    @Autowired
    private AvailableAuthorService availableAuthorService;

    @KafkaListener(topics = "author-topic", groupId = "author-group-v2", containerFactory = "kafkaListenerContainerFactory")
    public void handleAuthorEvent(AuthorEvent event) {
        System.out.println("приемник");
        if(event.getType().equals("SAVE")) {
            System.out.println("Получено событие: создан автор №" + event.getAuthorId());

            AvailableAuthor availableAuthor = new AvailableAuthor(event.getAuthorId(), event.getUsername(), event.getEmail());

            availableAuthorService.save(availableAuthor);
        } else if(event.getType().equals("EDIT")) {
            System.out.println("Получено событие: изменен автор №" + event.getAuthorId());

            availableAuthorService.editNickname(event.getAuthorId(), event.getUsername());
        } else if(event.getType().equals("DELETE")) {
            System.out.println("Получено событие: удален автор №" + event.getAuthorId());

            availableAuthorService.deleteById(event.getAuthorId());
        }
    }
}
