package com.assignment.vs.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "question_id")
    private Long questionId;
}
