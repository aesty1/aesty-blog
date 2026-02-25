package ru.denis.blog.rest_controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.denis.blog.kafka.KafkaProducer;
import ru.denis.blog.services.AuthorService;
import ru.denis.blog.services.CommentService;
import ru.denis.blog.services.PostService;

@RestController
public class TestController {

    private final KafkaProducer kafkaProducer;

    public TestController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @PostMapping("/kafka/add")
    public String test(@RequestBody String message) {

        kafkaProducer.sendMessage(message);

        return "Success";
    }

}
