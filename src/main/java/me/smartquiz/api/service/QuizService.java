package me.smartquiz.api.service;


import com.simiacryptus.text.TextGenerator;
import com.simiacryptus.text.gpt2.GPT2Util;
import io.micronaut.core.io.Readable;
import io.micronaut.core.io.ResourceLoader;
import me.smartquiz.api.config.OpenNlpModelConfig;
import me.smartquiz.api.domain.Question;
import me.smartquiz.api.domain.Quiz;
import me.smartquiz.api.domain.QuizType;
import me.smartquiz.api.model.Sentence;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton
public class QuizService {

	@Inject
	OpenNlpModelConfig openNlpModelConfig;

	final Logger logger = LoggerFactory.getLogger(QuizService.class);

	public Quiz create(QuizType quizType, String content) {
		logger.info(content);
		Quiz quiz = new Quiz();
		quiz.setId(UUID.randomUUID());
		quiz.setQuizType(quizType);
		quiz.setContent(content);
		List<String> sentences = getSentences(content);
		sentences.forEach(System.out::println);

		quiz.setQuestions(generateQuestionSet(quizType, sentences, 10));
		quiz.setCreatedOn(LocalDateTime.now());
		return quiz;
	}

	private List<String> getSentences(String content) {
		// TODO
		// Language detection
		try {
			InputStream inputStream = openNlpModelConfig.getSentModelEn().asInputStream();
			SentenceModel model = new SentenceModel(inputStream);
			SentenceDetectorME detector = new SentenceDetectorME(model);
			String sentences[] = detector.sentDetect(content);
			double[] sentenceProbabilities = detector.getSentenceProbabilities();

			return IntStream.range(0, sentences.length)
				.mapToObj(i -> new Sentence(sentenceProbabilities[i], sentences[i]))
				.sorted(Comparator.comparing(Sentence::getProbablity))
				.collect(Collectors.toList())
				.stream().map(s -> s.getSentence())
				.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return List.of();
	}

	private List<Question> generateQuestionSet(QuizType quizType, List<String> sentences, int quizSize) {
		TextGenerator textGenerator = GPT2Util.get345M();
		if(quizType.equals(QuizType.TFQ)){
			return IntStream.range(0, quizSize)
					.mapToObj(i -> {
						Question question = new Question();
						question.setId(UUID.randomUUID());
						if(quizType.equals(QuizType.TFQ)){
							question.setOptions(List.of("true", "false"));
							Random rand = new Random();
							String prefix = sentences.get(rand.nextInt(sentences.size()));
							question.setText(textGenerator.generateText(10, prefix));
						}
						return question;
					})
					.collect(Collectors.toList());
		}
		return List.of();
	}

}
