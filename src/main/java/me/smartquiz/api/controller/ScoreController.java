package me.smartquiz.api.controller;

import io.micronaut.http.annotation.*;

@Controller("/score")
public class ScoreController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}