package com.example.graphql2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Graphql2Application {

	public static void main(String[] args) {
		SpringApplication.run(Graphql2Application.class, args);
	}
	
	///to support Long type
    @Bean
    public graphql.schema.GraphQLScalarType extendedScalarLong() {
    	return graphql.scalars.ExtendedScalars.GraphQLLong;
    	}
}
