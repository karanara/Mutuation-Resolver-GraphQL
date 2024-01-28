package com.example.graphql2.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	public Author(Long id) {
	    this.id = id;
	  }
	public Author(String name, Integer age) {
	    this.name = name;
	    this.age = age;
	  }
	public Author() {
		// TODO Auto-generated constructor stub
	}
	
}
