package com.assignment.vs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.vs.domain.Question;
import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.domain.UserQuestion;
import com.assignment.vs.repository.QuestionRepository;
import com.assignment.vs.repository.UserQuestionRepository;
import com.assignment.vs.service.QuestionService;

@SpringBootTest
public class QuestionServiceTests {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private UserQuestionRepository userQuestionRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionService(questionRepository, userQuestionRepository);
    }

    @Test
    public void testCreate() {
        Question question = new Question();
        UserInfo user = new UserInfo();
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        Long questionId = questionService.create(question, user);
        verify(questionRepository).save(question);
        assertEquals(question.getId(), questionId);
    }

    @Test
    public void testUpdate() {
        Question question = new Question();
        question.setId(1L);
        when(questionRepository.save(question)).thenReturn(question);
        Long updatedQuestionId = questionService.update(question);
        verify(questionRepository).save(question);
        assertEquals(question.getId(), updatedQuestionId);
    }

    @Test
    public void testGetAll() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(questionRepository.findAll()).thenReturn(questions);
        List<Question> retrievedQuestions = questionService.getAll();
        verify(questionRepository).findAll();
        assertEquals(questions, retrievedQuestions);
    }

    @Test
    public void testGetById_QuestionNotFound() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        Question question = questionService.getById(1L);
        verify(questionRepository).findById(1L);
        assertNull(question);
    }

    @Test
    public void testGetById_QuestionFound() {
        Question question = new Question();
        question.setId(1L);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Question retrievedQuestion = questionService.getById(1L);
        verify(questionRepository).findById(1L);
        assertEquals(question, retrievedQuestion);
    }

    @Test
    public void testGetAllByUserIdAndContent() {
        Long userId = 1L;
        String content = "example";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(questionRepository.findAllByUserIdAndContent(userId, content)).thenReturn(questions);
        List<Question> retrievedQuestions = questionService.getAllByUserIdAndContent(userId, content);
        verify(questionRepository).findAllByUserIdAndContent(userId, content);
        assertEquals(questions, retrievedQuestions);
    }

    @Test
    public void testGetAllNotVotedQuestions() {
        Long userId = 1L;
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(questionRepository.findAllNotVotedQuestions(userId)).thenReturn(questions);
        List<Question> retrievedQuestions = questionService.getAllNotVotedQuestions(userId);
        verify(questionRepository).findAllNotVotedQuestions(userId);
        assertEquals(questions, retrievedQuestions);
    }

    @Test
    public void testGetAllVotedByUserId() {
        Long userId = 1L;
        List<UserQuestion> userQuestions = new ArrayList<>();
        userQuestions.add(new UserQuestion());
        userQuestions.add(new UserQuestion());
        Mockito.when(userQuestionRepository.findAllQuestionsVotedByUserId(userId)).thenReturn(userQuestions);
        List<UserQuestion> retrievedUserQuestions = questionService.getAllVotedByUserId(userId);
        Mockito.verify(userQuestionRepository).findAllQuestionsVotedByUserId(userId);
        Assertions.assertEquals(userQuestions, retrievedUserQuestions);
    }
}
