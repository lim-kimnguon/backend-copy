package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.Quiz;
import com.quiz.quizsystem.repository.QuizRepository;

@Service
@Transactional
public class QuizService {
  @Autowired
  private QuizRepository quizRepository;

  public List<Quiz> listAllQuizs() {
    return quizRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }

  public void saveQuiz(Quiz quiz) {
    quizRepository.save(quiz);
  }

  public Quiz getQuiz(Integer id) {
    return quizRepository.findByIdIs(id);
  }

  public int deleteQuiz(Integer id) {
    return quizRepository.DeleteQuiz(id);
  }

  public List<Quiz> getQuizByUserId(Integer user_id) {
    return quizRepository.findByUserId(user_id);
  }

  public List<Quiz> getQuiznotyetAssigntoUser(Integer user_id) {
    return quizRepository.findAllQuizNotYetAssigntoUser(user_id);
  }

}
