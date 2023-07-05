package com.assignment.vs.domain;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question is be Blank")
    @NotEmpty(message = "Question is Empty")
    // @Max(value=200, message="Question no more than 200 characters")
    private String question;

    @CreationTimestamp
    private LocalDateTime createTimessTime;
    
    private Long totalVotes;
    private Long likeVotes;
    private Long dislikeVotes;
    private Double percentageOfLike;
    private Double percentageOfDislike;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Transient
    private Long userId;
    // @ManyToMany(mappedBy = "votedQuestions")
    // private Set<UserInfo> users;
    
}
