package net.zerotodev.api.kafka.repository;

import net.zerotodev.api.kafka.domain.Attempt;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface AttemptRepository extends ReactiveMongoRepository<Attempt, String> {
    Optional<Attempt> findByAlias(String alias);
    @Query("{'alias':{$regex: ?0}}")
    Flux<Attempt> findRegexByAlias(String alias); // select * from attempts where alias like %alias%
    @Query("{'alias':{$regex: ?0}}")
    Flux<Attempt> findRegexByAliaWithPage(String alias, Pageable page);
    @Query("{id:?0}")
    Mono<Attempt> findById(String id); // select * from attempts where id = ?
    @Query("{quizNo:?0}")
    Mono<Attempt> findByQuizNo(int quizNo); // select * attempts where quizNo = ?
    @Query("{quizNo:{$lt:?0}}")
    Flux<Attempt> findLittleThanQuizNo(int QuizNo); // select * attempts where quizNo < ?
    @Query("{quizNo:{$gte:?0}}")
    Flux<Attempt> findGreaterThanEqualQuizNo(int QuizNo); // select * attempts where QuizNo >= ?
    @Query(value = "{alias:?0}",count = true)
    int countQuiz(String alias); // select count(*) from attempts where alias = ?
    @Query(value = "{alias:?0, quizNO:?1}")
    Mono<Attempt> findByAliasAndQuizNo(String alias, int quizNo); // select * from attempts where alias = ? and quizNo = ?

}
