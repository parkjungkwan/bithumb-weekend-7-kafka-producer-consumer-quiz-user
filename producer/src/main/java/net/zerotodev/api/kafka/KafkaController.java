package net.zerotodev.api.kafka;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.kafka.domain.Quiz;
import net.zerotodev.api.kafka.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer producer;
    private final QuizService quizService;

    @GetMapping
    public String home(){
        return "Hello Kafka";
    }

    @PostMapping
    public String sendMessage(@RequestParam("message") String message){
        System.out.println("############# sendMessage 진입 ###########");
        this.producer.sendMessage(message);
        return "Kafka Successfully";
    }

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes(){
        return quizService.getQuizzes();
    }
}
