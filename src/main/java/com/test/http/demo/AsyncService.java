package com.test.http.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncService {

    @Autowired
    private JSONPlaceHolderClient placeHolderClient;

    @Async
    public CompletableFuture<List<Post>> getPostsAsync() {
        log.info("STARTED AN ASYNC CALL");
        var results = placeHolderClient.getPosts();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(results);
    }

}
