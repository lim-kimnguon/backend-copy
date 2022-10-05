package com.quiz.quizsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.dto.RequestUserQuiz;
import com.quiz.quizsystem.model.UserQuiz;
import com.quiz.quizsystem.repository.UserQuizRepository;

@Service
@Transactional
public class UserQuizService {
  
  @Autowired
  private UserQuizRepository userQuizRepository;

  public List<UserQuiz> Listall() {
    return userQuizRepository.findAll();
  }

  public void DeleteQuizFromUser(Integer quiz_id, Integer user_id) {
    userQuizRepository.DeleteQuizFromUserQuiz(quiz_id, user_id);
  }

  public void add(Integer quiz_id, Integer user_id) {
    userQuizRepository.DeleteQuizFromUserQuiz(quiz_id, user_id);
  }

  public void AddQuizForUser(RequestUserQuiz requestQuizQuestion) {

    UserQuiz userQuiz = new UserQuiz();
    userQuiz.setQuiz_id(requestQuizQuestion.getQuiz_id());
    userQuiz.setUser_id(requestQuizQuestion.getUser_id());

    userQuizRepository.save(userQuiz);
  }

  public void addMultiQuizForUser(int user_id, int[] quiz_id) {

    List<UserQuiz> userQuizList = new ArrayList<>();
  
    for (int i = 0; i < quiz_id.length; i++) {
      userQuizList.add(new UserQuiz(user_id,quiz_id[i]));
    }

    userQuizRepository.saveAll(userQuizList);
  }

  public void DeleteMutipleQuizFromUser(int user_id, int[] quiz_id){
    
     userQuizRepository.DeleteMultipleQuizFromUserQuiz(user_id, quiz_id);
  }


}
