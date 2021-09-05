package net.zerotodev.api.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zerotodev.api.kafka.domain.Quiz;
import net.zerotodev.api.kafka.service.QuizService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class KafkaApplication {
	private final QuizService quizService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(KafkaApplication.class).run(args);
		context.getBean(KafkaApplication.class).run(context);
		context.close();
		//SpringApplication.run(KafkaApplication.class, args);
	}

	private void run(ConfigurableApplicationContext context) {
		log.info("############ ConfigurableApplicationContext Start ################## ");
		MessageChannel channel = context.getBean("MessageChannel : ", MessageChannel.class);
		List<Quiz> quizzes = quizService.getQuizzes();
		for(Quiz quiz: quizzes){
			Map<String, Object> headers = Collections.singletonMap(KafkaHeaders.TOPIC, "QUIZ-SHOW");
			channel.send(new GenericMessage<>(quiz.toString(), headers));
		}
		log.info("############ Publishing Closed ###############");
	}

}
