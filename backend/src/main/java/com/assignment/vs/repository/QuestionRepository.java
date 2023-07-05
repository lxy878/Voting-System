package com.assignment.vs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vs.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    
}
