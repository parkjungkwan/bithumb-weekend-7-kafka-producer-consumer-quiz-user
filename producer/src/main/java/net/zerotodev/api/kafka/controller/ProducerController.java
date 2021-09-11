package net.zerotodev.api.kafka.controller;

import lombok.RequiredArgsConstructor;

import net.zerotodev.api.kafka.domain.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "kafka-spring-producer";

    @GetMapping("/publish/{name}")
    public String postMessage(@PathVariable final String name){
        User user = new User();
        user.setId("blahblah");
        user.setName(name);
        user.setEmail(name+"@test.com");
        kafkaTemplate.send(TOPIC, user.toString());
        return "Message Published Successfully";
    }


}