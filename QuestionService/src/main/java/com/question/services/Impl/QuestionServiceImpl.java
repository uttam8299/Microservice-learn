package com.question.services.Impl;

import com.question.entities.Question;
import com.question.repositories.QuestionRepository;
import com.question.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;


    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> get() {
        return questionRepository.findAll();
    }

    @Override
    public Question getOne(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("question not found"));
    }

    @Override
    public List<Question> getQuestionByQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
