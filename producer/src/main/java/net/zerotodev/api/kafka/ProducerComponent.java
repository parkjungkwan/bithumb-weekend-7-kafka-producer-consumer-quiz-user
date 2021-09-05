package net.zerotodev.api.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class ProducerComponent {
    private static final String TOPIC = "sample";

    private final KafkaTemplate<String ,String > kafkaTemplate;

    public void sendMessage(String message){
        System.out.println("Producer Send Message : "+message);
    }
}
