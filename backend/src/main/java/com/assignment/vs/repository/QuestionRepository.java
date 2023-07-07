package com.assignment.vs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.vs.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    @Query(value = "select * from question where user_id=?1 order by create_timestamp desc", nativeQuery = true)
    public List<Question> findAllbyUserId(Long uid);

    @Query(value = "select * from question where user_id=?1 and content=?2", nativeQuery = true)
    public List<Question> findAllByUserIdAndContent(Long id, String content);

    @Query(value = "select * from question where id not in (select question_id from user_question where user_id=?1) order by create_timestamp desc", nativeQuery = true)
    public List<Question> findAllNotVotedQuestions(Long uid);
}
