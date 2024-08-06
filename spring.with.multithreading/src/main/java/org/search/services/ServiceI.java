package org.search.services;

import org.search.entities.response.ResponseResultThread;

import java.util.concurrent.CompletableFuture;

public interface ServiceI {

    CompletableFuture<ResponseResultThread> getTotal(String searchString);

}
