package com.test.http.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Post(
        @JsonProperty("userId") int userId,
        @JsonProperty("id") int id,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body) {
}
