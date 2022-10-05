package com.quiz.quizsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.dto.RequestQuizQuestion;
import com.quiz.quizsystem.model.QuizQuestion;
import com.quiz.quizsystem.repository.QuizQuestionRepository;

@Service
@Transactional
public class QuizQuestionService {

  @Autowired
  private QuizQuestionRepository quizquestionRepository;

  public List<QuizQuestion> Listall() {
    return quizquestionRepository.findAll();
  }

  public void DeleteQuestionFromQuiz(Integer quiz_id, Integer question_id) {
    quizquestionRepository.DeleteQuestionFromQuiz(quiz_id, question_id);
  }

  public void AddQuestionToQuiz(RequestQuizQuestion requestQuizQuestion) {

    QuizQuestion quizQuestion = new QuizQuestion();
    quizQuestion.setQuestion_id(requestQuizQuestion.getQuestion_id());
    quizQuestion.setQuiz_id(requestQuizQuestion.getQuiz_id());

    quizquestionRepository.save(quizQuestion);
  }

  public void addMultiQuestionToQuiz(int quiz_id, int[] question_id) {

    List<QuizQuestion> quizQuestionList = new ArrayList<>();
  
    for (int i = 0; i < question_id.length; i++) {
      quizQuestionList.add(new QuizQuestion(question_id[i],quiz_id));
    }
    // quizquestionRepository.saveAll(quizQuestionList);
    quizquestionRepository.saveAll(quizQuestionList);
  }

  public void DeleteMutipleQuestionFromQuiz(int quiz_id, int[] question_id){
    quizquestionRepository.DeleteMutipleQuestionFromQuiz(quiz_id, question_id);
  }

}
