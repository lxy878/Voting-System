package com.assignment.vs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.vs.domain.Question;
import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.domain.UserQuestion;
import com.assignment.vs.domain.UserQuestionKey;
import com.assignment.vs.repository.QuestionRepository;
import com.assignment.vs.repository.UserQuestionRepository;

@Service
public class QuestionService {
    
    private QuestionRepository questionRepository;
    private UserQuestionRepository userQuestionRepository;

    public QuestionService(QuestionRepository questionRepository, UserQuestionRepository userQuestionRepository){
        this.questionRepository = questionRepository;
        this.userQuestionRepository = userQuestionRepository;
    }

    public Long create(Question question, UserInfo user){
        question.setUser(user);
        return questionRepository.save(question).getId();
    }

    public Long update(Question question){
        return questionRepository.save(question).getId();
    }
    public List<Question> getAllByUserId(Long uid){
        return questionRepository.findAllbyUserId(uid);
    }

    public List<Question> getAll(){
        return questionRepository.findAll();
    }

    public Question getById(Long qid){
        Optional<Question> oq = questionRepository.findById(qid);
        if(!oq.isPresent()){
            return null;
        }
        return oq.get();
    }
    public List<Question> getAllByUserIdAndContent(Long id, String content){
        return questionRepository.findAllByUserIdAndContent(id, content);
    }
    
    public List<Question> getAllNotVotedQuestions(Long uid){
        return questionRepository.findAllNotVotedQuestions(uid);
    }
    
    public List<UserQuestion> getAllVotedByUserId(Long uid){
        return userQuestionRepository.findAllQuestionsVotedByUserId(uid);
    }
    
    public UserQuestion saveVote(UserInfo user, Question question, Boolean vote){
        UserQuestionKey uqk = new UserQuestionKey(user.getId(), question.getId());
        Optional<UserQuestion> oqu = userQuestionRepository.findById(uqk);
        if(oqu.isPresent()){
            return null;
        }
        UserQuestion uq = new UserQuestion();
        uq.setId(uqk);
        uq.setVotedUser(user);
        uq.setVotedQuestion(question);
        uq.setVote(vote);
        return userQuestionRepository.save(uq);
    }
}
