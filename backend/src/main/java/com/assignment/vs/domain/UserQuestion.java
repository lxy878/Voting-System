package com.assignment.vs.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestion {
    @EmbeddedId
    private UserQuestionKey id;
    
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    UserInfo votedUser;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name="question_id")
    Question votedQuestion;
    
    private Boolean vote;
}
