package com.example.SpringWebfluxMongo;

import com.example.SpringWebfluxMongo.webClientVariables.ChessWebclientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringWebfluxMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxMongoApplication.class, args);
	}

}
