package com.test.http.demo;

import java.util.Objects;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2Provider {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public String getAuthenticationToken(final String token) {
        var authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(token).principal("token")
                .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

        OAuth2AccessToken accessToken = Objects.requireNonNull(authorizedClient).getAccessToken();

        return String.format("Bearer %s", accessToken.getTokenValue());
    }
}