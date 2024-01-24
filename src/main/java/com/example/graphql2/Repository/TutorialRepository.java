package com.example.graphql2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql2.demo.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
