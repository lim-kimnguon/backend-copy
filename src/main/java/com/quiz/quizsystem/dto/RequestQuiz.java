package com.quiz.quizsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestQuiz {
  
  private String name;
  private String description;
  private int quiz_level_id;
  private int breaktime;



}
