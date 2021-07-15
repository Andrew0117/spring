package com.spring.async.service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

public interface HtmlService {

    CompletableFuture<String> getTitle(final String url)
            throws IOException, FailingHttpStatusCodeException, MalformedURLException;

}
