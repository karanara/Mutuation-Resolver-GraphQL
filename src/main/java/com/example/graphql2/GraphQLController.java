package com.example.graphql2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql2.Repository.AuthorRepository;
import com.example.graphql2.Repository.TutorialRepository;
import com.example.graphql2.Resolver.Mutation;
import com.example.graphql2.Resolver.QueryResolverClass;
import com.example.graphql2.Resolver.TutorialResolver;
import com.example.graphql2.demo.Author;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.language.ScalarTypeDefinition;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import jakarta.annotation.PostConstruct;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@RestController
public class GraphQLController {

    private GraphQL graphQL;
        
    @Autowired
    private Mutation mutation;
    
    @Autowired
    private TutorialResolver tutorialResolver;
    
    @Autowired
    private QueryResolverClass queryResolverClass;
    
    @Autowired
	private ResourceLoader resourceLoader;
    
   @Autowired
   private AuthorRepository authorRepository;
   
   @Autowired
   private TutorialRepository tutorialRepository;
   
   
   @GetMapping("/getAllAuthors")
   public List<Author> getAllAuthorsFromList(){
	   return authorRepository.findAll();
   }
    
    @PostConstruct
    public void loadSchema() throws IOException {
    	SchemaParser schemaParser = new SchemaParser();
    	InputStream authorInputStream = resourceLoader.getResource("classpath:graphql/author.graphqls").getInputStream();
         InputStream tutorialInputStream = resourceLoader.getResource("classpath:graphql/tutorial.graphqls").getInputStream();
         TypeDefinitionRegistry registry = new TypeDefinitionRegistry();
         registry.merge(schemaParser.parse(authorInputStream));
         registry.merge(schemaParser.parse(tutorialInputStream));
		//TypeDefinitionRegistry registry = new SchemaParser().parse(authorInputStream,tutorialInputStream);
		//GraphQLScalarType longScalar = ExtendedScalars.GraphQLLong;
        //registry.add(null)

		
	    RuntimeWiring wiring = buildWiring();
	    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
	    graphQL = GraphQL.newGraphQL(schema).build();
    }
	
	private RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring().scalar(ExtendedScalars.GraphQLLong)
                .type("Query", typeWriting ->
                        typeWriting.dataFetcher("findAllAuthors", env -> queryResolverClass.findAllAuthors(env))
                        .dataFetcher("countAuthors", env -> queryResolverClass.countAuthors())
                        .dataFetcher("findAllTutorial", env -> queryResolverClass.findAllTutorials())
                        .dataFetcher("countTutorials", env ->queryResolverClass.countTutorials())
                        .dataFetcher("getAuthor", env -> tutorialResolver.getAuthor(env.getSource())))
                .type("Mutation", typeWriting ->
                typeWriting.dataFetcher("createAuthor", env -> mutation.createAuthor(
                        env.getArgument("name"),
                        env.getArgument("age")
                ))
                        .dataFetcher("createTutorial", env -> mutation.createTutorial(
                                env.getArgument("title"),
                                env.getArgument("description"),
                                env.getArgument("authorId")
                        ))
                        .dataFetcher("deleteTutorial", env -> mutation.deleteTutorial(
                                env.getArgument("id")
                        ))
                        .dataFetcher("updateTutorial", env -> mutation.updateTutorial(
                                env.getArgument("id"),
                                env.getArgument("title"),
                                env.getArgument("description")
                        ))
                		   )
                .build();
	}

	@PostMapping("/graphql")
	public ResponseEntity<Object> getAll(@RequestBody String query){
		ExecutionResult result = graphQL.execute(query);
	    return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
}
