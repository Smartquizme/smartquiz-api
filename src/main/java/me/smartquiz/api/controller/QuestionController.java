package me.smartquiz.api.controller;

import io.micronaut.http.annotation.*;

@Controller("/question")
public class QuestionController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}