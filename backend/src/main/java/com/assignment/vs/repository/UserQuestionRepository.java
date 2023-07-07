package com.assignment.vs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.vs.domain.UserQuestion;
import com.assignment.vs.domain.UserQuestionKey;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, UserQuestionKey>{
    @Query(value = "select uq.* from user_question as uq inner join question as q on uq.question_id = q.id where uq.user_id = ?1", nativeQuery = true)
    public List<UserQuestion> findAllQuestionsVotedByUserId(Long uid);
}
