package com.test.http.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.openid.connect.sdk.assurance.Status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

    private final JSONPlaceHolderClient placeHolderClient;

    public TestController(JSONPlaceHolderClient placeHolderClient) {
        this.placeHolderClient = placeHolderClient;
    }

    @Operation(summary = "test api", description = "test api", tags = { "test" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Post.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })

    @GetMapping(value = "/test")
    public List<Post> getTestData(
            @Parameter(description = "trace-id") @RequestHeader(value = "Trace-Id", required = false) String traceId) {

        var users = GetPostsAsync();
        CompletableFuture.allOf(users).join();

        try {
            return users.get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    @Async
    public CompletableFuture<List<Post>> GetPostsAsync() {
        var results = placeHolderClient.getPosts();
        return CompletableFuture.completedFuture(results);
    }

}
