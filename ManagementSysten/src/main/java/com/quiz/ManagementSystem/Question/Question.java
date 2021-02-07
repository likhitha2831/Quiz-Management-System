package com.quiz.ManagementSystem.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @NotBlank(message = "questionContent is mandatory")
    @Getter
    private String questionContent;
    @NotBlank(message = "option1 is mandatory")
    @Getter
    private String option1;
    @NotBlank(message = "option2 is mandatory")
    @Getter
    private String option2;
    @NotBlank(message = "option3 is mandatory")
    @Getter
    private String option3;
    @NotBlank(message = "option4 is mandatory")
    @Getter
    private String option4;
    @NotBlank(message = "answer is mandatory")
    @Getter
    private String answer;
}
