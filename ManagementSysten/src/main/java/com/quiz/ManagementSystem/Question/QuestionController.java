package com.quiz.ManagementSystem.Question;

import com.quiz.ManagementSystem.exception.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/{id}", produces = "application/json")
    Question getQuestionDetails(@PathVariable Long id) throws QuestionNotFoundException {
        return questionService.fetchById(id);
    }

    @PostMapping
    ResponseEntity<String> addQuestionDetails(@RequestBody @Valid Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("added question successfully", HttpStatus.OK);
    }
}
