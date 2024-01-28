package com.example.graphql2.Resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphql2.Repository.AuthorRepository;
import com.example.graphql2.Repository.TutorialRepository;
import com.example.graphql2.demo.Author;
import com.example.graphql2.demo.Tutorial;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLScalarType;

@Component
public class QueryResolverClass implements GraphQLQueryResolver{

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
	          .aliasedScalar(ExtendedScalars.GraphQLLong)
	          .build();
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private TutorialRepository tutorialRepository;
	
	public Iterable<Author> findAllAuthors(DataFetchingEnvironment env){
		System.out.println(authorRepository.findAll());
		return authorRepository.findAll();
	}
	
	public List<Tutorial> findAllTutorials(){
		return tutorialRepository.findAll();
	}
	
	public long countAuthors() {
		return authorRepository.count();
	}
	
	public long countTutorials() {
		return tutorialRepository.count();
	}
}
