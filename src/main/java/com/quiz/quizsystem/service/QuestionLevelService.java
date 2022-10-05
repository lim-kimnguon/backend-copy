package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.QuestionLevel;
import com.quiz.quizsystem.repository.QuestionLevelRepository;

@Service
@Transactional
public class QuestionLevelService {
    @Autowired
    private QuestionLevelRepository questionLevelRepository;

    public List<QuestionLevel> ListAllQuestionLevel(){
      return questionLevelRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
    }
    public void saveQuestionLevel(QuestionLevel questionLevel){
      questionLevelRepository.save(questionLevel);
    }
  
    public QuestionLevel getQuestionLevel(Integer id){
      return questionLevelRepository.findById(id).get();
    }
    
    public int deleteQuestionLevel(Integer id){
      return questionLevelRepository.DeleteQuestionLevels(id);
    }



}
