package com.quiz.services.impl;

import com.quiz.entitites.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepository.findAll();

        List<Quiz> newQuizzes = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionsByQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

        return newQuizzes;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("quiz not found"));
        quiz.setQuestions(questionClient.getQuestionsByQuiz(id));
        return quiz;
    }
}
