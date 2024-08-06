package org.search.services.google;

import org.search.components.app.ApplicationComponent;
import org.search.entities.GoogleEntity;
import org.search.entities.enumaration.SearchService;
import org.search.entities.response.ResponseGoogleSearch;
import org.search.entities.response.ResponseResultThread;
import org.search.services.ServiceI;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service(value = "googleService")
@Scope(value = "singleton")
public class GoogleService implements ServiceI {

    private final GoogleEntity googleEntity;

    private final RestTemplate restTemplate;

    private final ExecutorService executor;

    public GoogleService(ApplicationComponent applicationComponent) {
        this.googleEntity = (GoogleEntity) applicationComponent.getSearchServiceMap().get(SearchService.GOOGLE);
        this.restTemplate = new RestTemplate();
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    @Async
    public CompletableFuture<ResponseResultThread> getTotal(String searchString) {
        String[] words = searchString.split(" ");

        return CompletableFuture.supplyAsync(() -> {
            Long total = 0L;

            for (String word : words) {
                try {
                    String url = this.googleEntity.getBaseUrl() + "q=" + word.trim() + "&key=" + this.googleEntity.getApi_key() + "&cx=" + this.googleEntity.getSearch_engine_id();

                    ResponseEntity<ResponseGoogleSearch> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, ResponseGoogleSearch.class);

                    ResponseGoogleSearch responseGoogleSearch = responseEntity.getBody();
                    total += responseGoogleSearch.getSearchInformation().getTotalResults();
                } catch (Exception e) {
                    // log
                    System.out.println(e.getMessage());
                }
            }

            return new ResponseResultThread(total);
        }, executor);

    }

}
