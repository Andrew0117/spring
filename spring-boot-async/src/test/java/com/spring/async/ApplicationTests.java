package com.spring.async;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class ApplicationTests {

    private final static String HOST = "http://localhost:8082";

    @Test
    public void callEndpoint() throws ExecutionException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture<String> facebook =
                CompletableFuture.supplyAsync(() ->
                        restTemplate.getForObject(HOST + "/api/title?resource=0", String.class));
        CompletableFuture<String> github =
                CompletableFuture.supplyAsync(() ->
                        restTemplate.getForObject(HOST + "/api/title?resource=1", String.class));
        CompletableFuture<String> pinterest =
                CompletableFuture.supplyAsync(() ->
                        restTemplate.getForObject(HOST + "/api/title?resource=2", String.class));

        CompletableFuture.allOf(facebook/*, yahoo*/, pinterest).join();

        String facebookResponse = facebook.get();
        String githubResponse = github.get();
        String pinterestResponse = pinterest.get();

        Assertions.assertEquals("Facebook", facebookResponse.substring(0, 8));
        Assertions.assertEquals("GitHub", githubResponse.substring(0, 6));
        Assertions.assertEquals("Pinterest", pinterestResponse.substring(0, 9));
    }

}
