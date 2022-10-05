package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.QuestionType;
import com.quiz.quizsystem.repository.QuestionTypeRepository;

@Service
@Transactional
public class QuestionTypeService {
  @Autowired
  private QuestionTypeRepository questionTypeRepository;

  public List<QuestionType> ListAllQuestionType(){
    return questionTypeRepository.findByDeleteFalse();
  }
  public void saveQuestionType(QuestionType questionLevel){
    questionTypeRepository.save(questionLevel);
  }

  public QuestionType getQuestionType(Integer id){
    return questionTypeRepository.findById(id).get();
  }
  
  public int deleteQuestionType(Integer id){
    return questionTypeRepository.DeleteQuestionType(id);
  }


}