package com.example.graphql2.Resolver;
import com.example.graphql2.Repository.AuthorRepository;
import com.example.graphql2.demo.Author;
import com.example.graphql2.demo.Tutorial;

import graphql.kickstart.tools.GraphQLResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorialResolver implements GraphQLResolver<Tutorial>{

	@Autowired
	private AuthorRepository authorRepository;
	
	public Author getAuthor(Tutorial tutorial) {
		return authorRepository.findById(tutorial.getAuthor().getId()).orElseThrow(null);
	}
	
}
