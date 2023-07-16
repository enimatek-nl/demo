package com.test.http.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.core.http.HttpClient;
import com.azure.core.http.jdk.httpclient.JdkHttpClientBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

@Configuration
public class SecretClientConfiguration {

    @Bean
    public SecretClient createSecretClient() {
        HttpClient client = new JdkHttpClientBuilder().build();
        return new SecretClientBuilder()
                .vaultUrl("https://abc.vault.azure.net/")
                .credential(new DefaultAzureCredentialBuilder().build())
                .httpClient(client)
                .buildClient();
    }

}