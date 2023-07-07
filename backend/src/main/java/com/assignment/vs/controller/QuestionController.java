package com.assignment.vs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.assignment.vs.service.QuestionService;
import com.assignment.vs.service.UserService;

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
