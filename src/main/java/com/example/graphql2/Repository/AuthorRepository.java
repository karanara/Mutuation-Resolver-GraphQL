package com.example.graphql2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphql2.demo.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
