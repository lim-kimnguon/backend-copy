package com.quiz.quizsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_quizzes", schema = "quiz")
public class UserQuiz {
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY)
  private int id;

  private int user_id;
  private int quiz_id;

  public UserQuiz(int user_id, int quiz_id) {
    this.user_id = user_id;
    this.quiz_id = quiz_id;
  }



}
