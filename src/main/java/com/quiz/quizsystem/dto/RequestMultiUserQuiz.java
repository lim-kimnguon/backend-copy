package com.quiz.quizsystem.dto;

public class RequestMultiUserQuiz {
  int user_id;
  int[] quiz_id;
  
  public int getUser_id() {
    return user_id;
  }
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public int[] getQuiz_id() {
    return quiz_id;
  }
  public void setQuiz_id(int[] quiz_id) {
    this.quiz_id = quiz_id;
  }

 
  
}
