package com.quiz.quizsystem.service;

import com.quiz.quizsystem.dto.RequestQuestionAnswer;
import com.quiz.quizsystem.model.QuestionAnswer;
import com.quiz.quizsystem.repository.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionAnswerService {

    @Autowired QuestionAnswerRepository questionAnswerRepository;

    public List<QuestionAnswer> getAll() {
        return this.questionAnswerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<QuestionAnswer> getById(int id) {
        return this.questionAnswerRepository.findById(id);
    }

    public List<QuestionAnswer> addQuestionAnswer(RequestQuestionAnswer[] requestQuestionAnswer) {
        List<QuestionAnswer> questionAnswers = new ArrayList<>();

        for (RequestQuestionAnswer request: requestQuestionAnswer) {
            QuestionAnswer questionAnswer = new QuestionAnswer();

            questionAnswer.setName(request.getName());
            questionAnswer.setScore(request.getScore());
            questionAnswer.setOrder_number(request.getOrderNumber());
            questionAnswer.setAnswer_id(request.getAnswerId());
            questionAnswer.setUser_id(request.getUserId());
            System.out.println(questionAnswer.getName() + " " + questionAnswer.getAnswer_id());
            questionAnswers.add(questionAnswer);
        }

        return this.questionAnswerRepository.saveAll(questionAnswers);
    }

    public void deleteQuestionAnswer(int id) {
        this.questionAnswerRepository.deleteById(id);
    }
}
