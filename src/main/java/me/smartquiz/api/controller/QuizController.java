package me.smartquiz.api.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.util.HttpHostResolver;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.web.router.RouteBuilder;
import me.smartquiz.api.domain.Quiz;
import me.smartquiz.api.domain.QuizRequest;
import me.smartquiz.api.service.QuizService;

import javax.inject.Inject;
import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller("/quiz")
public class QuizController {

    @Inject
    HttpHostResolver httpHostResolver;
    @Inject
    RouteBuilder.UriNamingStrategy uriNamingStrategy;
    @Inject
    QuizService quizService;

    @Post(consumes="application/json")
    public HttpResponse create(
            @Body QuizRequest quizRequest,
            HttpRequest httpRequest
    ) {
        Quiz quiz = quizService.create(quizRequest.getQuizType(), quizRequest.getContent());
        UUID id = quiz.getId();
        URI uri = UriBuilder
            .of(httpHostResolver.resolve(httpRequest))
            .path(uriNamingStrategy.resolveUri(QuizController.class))
            .path(id.toString())
            .build();
        return HttpResponse.created(uri).body(quiz);
    }

}
