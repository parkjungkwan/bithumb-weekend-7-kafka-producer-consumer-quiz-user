package net.zerotodev.api.kafka.service;

import net.zerotodev.api.kafka.domain.Quiz;
import reactor.core.publisher.Mono;

public interface QuizService {
    Mono<Quiz> creteQuiz();
}