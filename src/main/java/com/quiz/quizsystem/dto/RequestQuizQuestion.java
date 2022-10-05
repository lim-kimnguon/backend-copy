package com.quiz.quizsystem.dto;

public class RequestQuizQuestion {
  
  int quiz_id;
  int question_id;

  public int getQuiz_id() {
    return quiz_id;
  }
  public void setQuiz_id(int quiz_id) {
    this.quiz_id = quiz_id;
  }
  public int getQuestion_id() {
    return question_id;
  }
  public void setQuestion_id(int question_id) {
    this.question_id = question_id;
  }
  
}
