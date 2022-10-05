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
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "quiz_questions",schema = "quiz")
public class QuizQuestion {
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY)
  private int id;

  private int question_id;
  private int quiz_id;

  
  public QuizQuestion(int question_id, int quiz_id) {
    this.question_id = question_id;
    this.quiz_id = quiz_id;
  }




}
