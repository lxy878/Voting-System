package com.assignment.vs.service;

import java.util.List;

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

    public List<Question> getAllByUserId(Long uid){
        return questionRepository.findAllbyUserId(uid);
    }

    public List<Question> getAll(){
        return questionRepository.findAll();
    }

    public List<Question> getAllByUserIdAndContent(Long id, String content){
        return questionRepository.findAllByUserIdAndContent(id, content);
    }
}
