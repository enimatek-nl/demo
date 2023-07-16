package com.test.http.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import com.azure.security.keyvault.secrets.SecretClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Configuration
public class OAuth2Config {

    private final SecretClient secretClient;

    @Bean
    ClientRegistration myAuthClientRegistration(
            @Value("${spring.security.oauth2.client.provider.my-auth.token-uri}") String token_uri,
            @Value("${spring.security.oauth2.client.registration.my-auth.client-id}") String client_id,
            @Value("${spring.security.oauth2.client.registration.my-auth.scope}") String scope,
            @Value("${spring.security.oauth2.client.registration.my-auth.authorization-grant-type}") String authorizationGrantType) {

        //var client_secret = secretClient.getSecret("my-secret").getValue();
        var client_secret = "";

        return ClientRegistration
                .withRegistrationId("my-auth")
                .tokenUri(token_uri)
                .clientId(client_id)
                .clientSecret(client_secret)
                .scope(scope)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType))
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration myAuthClientRegistration) {
        return new InMemoryClientRegistrationRepository(myAuthClientRegistration);
    }

    /**
     * creates AuthManager in spring context for OAuth token management in InMemory
     * cache.
     *
     * @param clientRegistrationRepository - repo to retrieve auto configured
     *                                     registrations in spring
     *                                     context.
     * @param authorizedClientService      - service to fetch & refresh auth token
     *                                     in memory.
     * @return AuthorizedClientManager
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository,
                authorizedClientService);
    }
}