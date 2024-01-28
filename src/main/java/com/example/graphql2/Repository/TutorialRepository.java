package com.example.graphql2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphql2.demo.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
