package com.quiz.quizsystem.dto;

public class RequestQuestion {

  private String name;
  private int timeout;
  private int score;
  private int question_type_id;
  private int question_level_id;

  public RequestQuestion() {
  }
  public RequestQuestion(String name, int timeout, int score, int question_type_id, int question_level_id) {
    this.name = name;
    this.timeout = timeout;
    this.score = score;
    this.question_type_id = question_type_id;
    this.question_level_id = question_level_id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getTimeout() {
    return timeout;
  }
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
  public int getScore() {
    return score;
  }
  public void setScore(int score) {
    this.score = score;
  }
  public int getQuestion_type_id() {
    return question_type_id;
  }
  public void setQuestion_type_id(int question_type_id) {
    this.question_type_id = question_type_id;
  }
  public int getQuestion_level_id() {
    return question_level_id;
  }
  public void setQuestion_level_id(int question_level_id) {
    this.question_level_id = question_level_id;
  }

  
}
