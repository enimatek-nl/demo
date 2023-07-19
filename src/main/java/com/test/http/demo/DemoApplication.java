package com.test.http.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(DemoApplication.class, args);
	}

}
