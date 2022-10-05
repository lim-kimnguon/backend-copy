package com.quiz.quizsystem.controller;

import java.io.Console;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizsystem.dto.RequestMultiUserQuiz;
import com.quiz.quizsystem.dto.RequestUserQuiz;
import com.quiz.quizsystem.model.UserQuiz;
import com.quiz.quizsystem.service.UserQuizService;

@RestController
@RequestMapping("/userquiz")
public class UserQuizController {
  
  @Autowired
  private UserQuizService userQuizService;

  @GetMapping
  public List<UserQuiz> listData(){
    return userQuizService.Listall();
  }

  @PostMapping
  public void AddQuizForUser(@RequestBody RequestUserQuiz requestUserQuiz){
    userQuizService.AddQuizForUser(requestUserQuiz);
  }

  @DeleteMapping("/{user_id}/{quiz_id}")
  public void RemoveQuizFromUser(@PathVariable Integer quiz_id,@PathVariable Integer user_id ){
    userQuizService.DeleteQuizFromUser(quiz_id,user_id);
  }

  @PostMapping("/multiple")
  public void AddMultipleQuiz(@RequestBody RequestMultiUserQuiz requestMultiUserQuiz){
    userQuizService.addMultiQuizForUser(requestMultiUserQuiz.getUser_id(),requestMultiUserQuiz.getQuiz_id());
  }

  @PostMapping("/deletemultiple")
  void DeleteMultipleQuestion(@RequestBody RequestMultiUserQuiz requestMultiQuizQuestion) {
    try{
        userQuizService.DeleteMutipleQuizFromUser(requestMultiQuizQuestion.getUser_id(),requestMultiQuizQuestion.getQuiz_id());   
    }catch(NoSuchElementException e){
      System.out.println(e);
    }
  }
}
