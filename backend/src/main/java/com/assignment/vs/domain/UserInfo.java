package com.assignment.vs.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is blank")
    @NotEmpty(message = "Name is Empty")
    @Size(min=5, max=20, message = "length of name between 5 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only Alphanumeric Characters")
    private String name;

    // @ManyToMany
    // @JoinTable(
    //     name = "user_question",
    //     joinColumns = @JoinColumn(name="user_id"),
    //     inverseJoinColumns = @JoinColumn(name="question_id")
    // )
    // private Set<Question> votedQuestions;
}
