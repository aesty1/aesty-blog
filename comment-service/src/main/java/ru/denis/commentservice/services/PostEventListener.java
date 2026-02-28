package ru.denis.commentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.denis.commentservice.models.AvailablePost;
import ru.denis.common.dto.PostEvent;

@Service
public class PostEventListener {

    @Autowired
    private AvailablePostService availablePostService;

    @KafkaListener(topics = "post-topic", groupId = "comment-service-group-v2")
    public void handlePostEvent(PostEvent event) {
        if(event.getType().equals("SAVE")) {
            System.out.println("Получено событие: создан пост №" + event.getPostId());

            AvailablePost post = new AvailablePost(event.getPostId(), event.getTitle());

            availablePostService.save(post);
        } else if(event.getType().equals("EDIT")) {
            System.out.println("Получено событие: изменен пост №" + event.getPostId());

            availablePostService.editTitle(event.getPostId(), event.getTitle());
        } else if(event.getType().equals("DELETE")) {
            System.out.println("Получено событие: удален пост №" + event.getPostId());

            availablePostService.deleteById(event.getPostId());
        }

    }
}
