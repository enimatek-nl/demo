package com.test.http.demo;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FeignClientConfiguration {

    private final static String AUTH_SERVER_NAME = "my-auth";

    private final OAuth2Provider oauth2Provider;

    @Bean
    public HttpClient client() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (requestAttributes != null) {
                var traceId = requestAttributes.getRequest().getHeader("Trace-Id");
                requestTemplate.header("Trace-Id", traceId);
            }

            // oauth impl. spring security
            requestTemplate.header(HttpHeaders.AUTHORIZATION, oauth2Provider.getAuthenticationToken(AUTH_SERVER_NAME));
        };
    }

}
