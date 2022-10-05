package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.Answer;
import com.quiz.quizsystem.repository.AnswerRepository;

@Service
@Transactional
public class AnswerService {
  
  @Autowired
  private AnswerRepository answerRepository;
  private int deleteAnswerById;

  public List<Answer> ListAllAnswer(){
    return answerRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }

  public void saveAnswer(Answer answer){
    answerRepository.save(answer);
  }

  public Answer getAnswer(Integer id){
    return answerRepository.findById(id).get();
  }

  public List<Answer> getAnswerByQuestionId(Integer id){
    return answerRepository.findAllByQuestionIsAndDeleteFalse(id,Sort.by(Sort.Order.desc("id")));
  }
  
  public int deleteAswer(Integer id){
    deleteAnswerById = answerRepository.DeleteAnswerById(id);
    return deleteAnswerById;
  }

  public void deleteMultianswer(int question_id, String[] name){
    answerRepository.DeleteMultipleAnswer(question_id,name);
  }

}
