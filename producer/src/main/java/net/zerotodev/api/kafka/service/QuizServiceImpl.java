package net.zerotodev.api.kafka.service;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.kafka.domain.Quiz;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service @RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
    private final GeneratorService service;

    @Override
    public Mono<Quiz> creteQuiz() {
        int factorA = service.randomFactor();
        int factorB = service.randomFactor();
        Quiz quiz = new Quiz(factorA, factorB);
        return Mono.just(quiz);
    }
}