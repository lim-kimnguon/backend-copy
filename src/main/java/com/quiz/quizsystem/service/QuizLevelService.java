package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quiz.quizsystem.model.QuizLevel;
import com.quiz.quizsystem.repository.QuizLevelRepository;

@Service
@Transactional
public class QuizLevelService {
  
  @Autowired
  private QuizLevelRepository quizLevelRepository;

  
  public List<QuizLevel> ListAllQuizLevel(){
    return quizLevelRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }
  public void saveQuizLevel(QuizLevel quizLevel){
    quizLevelRepository.save(quizLevel);
  }

  public QuizLevel getQuizLevel(Integer id){
    return quizLevelRepository.findById(id).get();
  }
  
  public int deleteQuizLevel(Integer id){
    return quizLevelRepository.DeleteQuizLevel(id);
  }

}
