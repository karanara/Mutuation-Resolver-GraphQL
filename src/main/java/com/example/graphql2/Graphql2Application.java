package com.example.graphql2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;

//import graphql.scalars.ExtendedScalars;
//import graphql.schema.GraphQLScalarType;

@SpringBootApplication
public class Graphql2Application {

	public static void main(String[] args) {
		SpringApplication.run(Graphql2Application.class, args);
	}
	
	///to support Long type
	/*@Bean
    public GraphQLScalarType longScalar() {
        return ExtendedScalars.newAliasedScalar("Long")
                .aliasedScalar(ExtendedScalars.GraphQLLong)
                .build();
    }*/
	@Bean 
	public RuntimeWiringConfigurer runtimeWiringConfigurer() { 
	   return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLLong); 
	}
}
