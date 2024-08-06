package org.search.controllers;

import org.search.entities.response.ResponseResultThread;
import org.search.model.Search;
import org.search.services.ServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class SearchController {

    private ServiceI googleService;

    private ServiceI yahooService;

    public SearchController(ServiceI googleService, ServiceI yahooService) {
        this.googleService = googleService;
        this.yahooService = yahooService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search_Get(Model model) {

        model.addAttribute("search", new Search());
        model.addAttribute("googleTotal", 0L);
        model.addAttribute("yahooTotal", 0L);

        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search_Post(@ModelAttribute(value = "search") Search search, Model model) {

        CompletableFuture<ResponseResultThread> googleFuture = this.googleService.getTotal(search.getSearch());
        CompletableFuture<ResponseResultThread> yahooFuture = this.yahooService.getTotal(search.getSearch());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(googleFuture, yahooFuture);

        allOf.join();

        Long googleTotal = 0L;
        Long yahooTotal = 0L;

        try {
            googleTotal = googleFuture.get().getTotal();
            yahooTotal = yahooFuture.get().getTotal();
        } catch (InterruptedException | ExecutionException ex) {
            // log
            System.out.println(ex.getMessage());
        }

        model.addAttribute("search", search);
        model.addAttribute("googleTotal", googleTotal);
        model.addAttribute("yahooTotal", yahooTotal);

        return "search";
    }

}
