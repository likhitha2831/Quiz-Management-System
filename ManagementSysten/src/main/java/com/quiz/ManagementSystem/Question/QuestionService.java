package com.quiz.ManagementSystem.Question;

import com.quiz.ManagementSystem.exception.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question fetchById(Long id) throws QuestionNotFoundException {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(String.format("Question with id %d not found", id)));
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }
}
