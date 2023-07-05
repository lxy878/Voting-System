package com.assignment.vs.service;

import org.springframework.stereotype.Service;

import com.assignment.vs.domain.Question;
import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.repository.QuestionRepository;

@Service
public class QuestionService {
    
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Long create(Question question, UserInfo user){
        question.setUser(user);
        return questionRepository.save(question).getId();
    }
}
