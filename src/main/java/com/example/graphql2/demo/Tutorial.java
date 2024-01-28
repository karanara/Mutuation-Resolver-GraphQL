package com.example.graphql2.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Tutorial {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="author_id",nullable=false,updatable=false)
	private Author author;
}
