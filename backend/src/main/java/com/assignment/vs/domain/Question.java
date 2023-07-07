package com.assignment.vs.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Question is Blank")
    @NotEmpty(message = "Question is Empty")
    @Size(max=200, message = "Question no more than 200 characters")
    private String content;

    @CreationTimestamp
    private LocalDateTime createTimestamp;
    
    private Integer totalVotes=0;
    private Integer yesVotes=0;
    private Integer noVotes=0;
    private Float percentageOfYes=0.00f;
    private Float percentageOfNo=0.00f;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Transient
    private Long userId;

    // @ManyToMany(mappedBy = "votedQuestions")
    // private Set<UserInfo> users;
    
}
