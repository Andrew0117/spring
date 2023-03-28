package com.spring.async.controller;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.spring.async.service.HtmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class HtmlController {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlController.class);

    @Autowired
    HtmlService htmlService;

    @GetMapping("/title")
    public ResponseEntity<String> getTitle(
            @RequestParam(value = "resource", required = true) Integer resourceId
    ) {
        String resource;
        switch (resourceId) {
            case 0:
                resource = "https://www.facebook.com/";
                break;
            case 1:
                resource = "https://github.com/";
                break;
            default:
                resource = "https://www.pinterest.com/";
        }

        try {
            CompletableFuture<String> completableFuture = htmlService.getTitle(resource);
            return new ResponseEntity<>(completableFuture.get(), HttpStatus.OK);
        } catch (InterruptedException | ExecutionException | IOException | FailingHttpStatusCodeException ex) {
            LOG.warn(ex.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
