package me.smartquiz.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Singleton;

import me.smartquiz.api.domain.Question;
import me.smartquiz.api.domain.Quiz;
import me.smartquiz.api.domain.QuizType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class QuizService {

	final Logger logger = LoggerFactory.getLogger(QuizService.class);

	public Quiz create(QuizType quizType, String content) {
		logger.info(content);
		Quiz quiz = new Quiz();
		quiz.setId(UUID.randomUUID());
		quiz.setQuizType(quizType);
		quiz.setContent(content);
		quiz.setQuestions(generateQuestionSet());
		quiz.setCreatedOn(LocalDateTime.now());
		return quiz;
	}

	private List<Question> generateQuestionSet() {
		return IntStream.range(0, 10)
			.mapToObj(i -> {
				Question question = new Question();
				question.setId(UUID.randomUUID());
				return question;
			})
			.collect(Collectors.toList());
	}

}
