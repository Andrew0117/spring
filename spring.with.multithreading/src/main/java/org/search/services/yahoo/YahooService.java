package org.search.services.yahoo;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.search.components.app.ApplicationComponent;
import org.search.entities.YahooEntity;
import org.search.entities.enumaration.SearchService;
import org.search.entities.response.ResponseResultThread;
import org.search.services.ServiceI;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service(value = "yahooService")
@Scope(value = "singleton")
public class YahooService implements ServiceI {

    private final YahooEntity yahooEntity;

    private final ExecutorService executor;

    public YahooService(ApplicationComponent applicationComponent) {
        this.yahooEntity = (YahooEntity) applicationComponent.getSearchServiceMap().get(SearchService.YAHOO);
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    @Async
    public CompletableFuture<ResponseResultThread> getTotal(String searchString) {
        String[] words = searchString.split(" ");

        return CompletableFuture.supplyAsync(() -> {
            Long total = 0L;

            try (WebClient webClient = new WebClient(BrowserVersion.FIREFOX)) {
                webClient.getOptions().setJavaScriptEnabled(false);
                webClient.getOptions().setCssEnabled(false);
                for (String word : words) {
                    try {
                        HtmlPage page = webClient.getPage(this.yahooEntity.getBaseUrl() + "p=" + word.trim());
                        HtmlElement element = (HtmlElement) page.getByXPath("//span[@class='fz-14 lh-22']").get(0);
                        String text = element.asNormalizedText();
                        String[] result = text.split(" ");
                        String total_ = result[1].replace(",", "");
                        total += Long.parseLong(total_);
                    } catch (IOException e) {
                        // log
                        System.out.println(e.getMessage());
                    }
                }
            }

            return new ResponseResultThread(total);
        }, executor);
    }

}
