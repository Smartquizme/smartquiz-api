package me.smartquiz.api.controller;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class QuestionControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

//    @Test
//    public void testIndex() throws Exception {
//        assertEquals(HttpStatus.OK, client.toBlocking().exchange("/question").status());
//    }
}
