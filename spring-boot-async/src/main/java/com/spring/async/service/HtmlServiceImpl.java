package com.spring.async.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

@Service
public class HtmlServiceImpl implements HtmlService {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlServiceImpl.class);

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> getTitle(final String url)
            throws IOException, FailingHttpStatusCodeException, MalformedURLException {
        LOG.info("Execute the URL to get the title {} ", url);
        LOG.info("Current thread name {} ", Thread.currentThread().getName());
        WebClient webClient = new WebClient(getBrowser());
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);
        String title = htmlPage.getTitleText();

        return CompletableFuture.completedFuture(title);
    }

    private static final BrowserVersion getBrowser() {
        BrowserVersion browser;
        String applicationName = "Netscape";
        String applicationVersion = "5.0 (Windows)";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0";
        int browserVersionNumeric = 24;
        browser = new BrowserVersion(applicationName, applicationVersion, userAgent, browserVersionNumeric) {
            @Override
            public boolean hasFeature(BrowserVersionFeatures property) {
                return BrowserVersion.FIREFOX_24.hasFeature(property);
            }
        };
        return browser;
    }
}
