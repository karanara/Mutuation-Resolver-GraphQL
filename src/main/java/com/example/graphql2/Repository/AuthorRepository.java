package com.example.graphql2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql2.demo.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
