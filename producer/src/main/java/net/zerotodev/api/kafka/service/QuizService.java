package net.zerotodev.api.kafka.service;

import net.zerotodev.api.kafka.domain.Quiz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public interface QuizService {
    Quiz creteQuiz();
    List<Quiz> getQuizzes();
}