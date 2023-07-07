package com.assignment.vs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.vs.domain.Question;
import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.domain.UserQuestion;
import com.assignment.vs.service.QuestionService;
import com.assignment.vs.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;
    private UserService userService;

    public QuestionController(QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody Question question){
        UserInfo ui = userService.getUser(question.getUserId());
        if(ui==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: Not Found");
        }
        List<Question> qs = questionService.getAllByUserIdAndContent(ui.getId(), question.getContent());
        if(!qs.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question: content already existed");
        }
        Long id = questionService.create(question, ui);
        return ResponseEntity.ok().body("Question is created with "+id);
    }

    @GetMapping("/all/{uid}")
    public ResponseEntity<Object> getAllByUser(@PathVariable Long uid){
        UserInfo ui = userService.getUser(uid);
        if(ui==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: Not Found");
        }
        List<Question> qs = questionService.getAllByUserId(ui.getId());
        return ResponseEntity.ok().body(qs);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok().body(questionService.getAll());
    }

    @PostMapping("/vote")
    public ResponseEntity<Object> voteQuestion(@RequestBody JsonNode json){
        Long uid  = json.get("uid").asLong();
        UserInfo user = userService.getUser(uid);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: Not Found");
        }
        Long qid = json.get("qid").asLong();
        Question question = questionService.getById(qid);
        if(question == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question: Not Found");
        }
        Boolean vote = json.get("vote").asBoolean();
        UserQuestion uq = questionService.saveVote(user, question, vote);
        if(uq==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vote: User already Voted the Question");
        }
        // update question
        question.setTotalVotes(question.getTotalVotes()+1);
        if(vote) question.setYesVotes(question.getYesVotes()+1);
        else question.setNoVotes(question.getNoVotes()+1);
        question.setPercentageOfYes((float)question.getYesVotes()/question.getTotalVotes());
        question.setPercentageOfNo((float)question.getNoVotes()/question.getTotalVotes());
        questionService.update(question);

        return ResponseEntity.ok().body("vote id: "+uq.getId().toString());
        
    }

    @GetMapping("/goVote/{uid}")
    public ResponseEntity<Object> getAllNotVoted(@PathVariable Long uid){
        UserInfo user = userService.getUser(uid);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: Not Found");
        }
        List<Question> lq = questionService.getAllNotVotedQuestions(uid);
        return ResponseEntity.ok().body(lq);
    }

    @GetMapping("/voted/{uid}")
    public ResponseEntity<Object> getAllVoted(@PathVariable Long uid){
        UserInfo user = userService.getUser(uid);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: Not Found");
        }
        List<UserQuestion> uq = questionService.getAllVotedByUserId(uid);
        return ResponseEntity.ok().body(uq);
    }

    // handle ConstraintViolationException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException notValidEx){
        Map<String, String> errors = new HashMap<>();
        notValidEx.getBindingResult().getAllErrors().forEach(e->{
            errors.put(((FieldError) e).getField(), e.getDefaultMessage());
        });
        return errors;
    }
}
