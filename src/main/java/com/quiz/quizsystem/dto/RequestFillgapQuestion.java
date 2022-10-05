package com.quiz.quizsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFillgapQuestion {
  
  private String name;
  private int timeout;
  private int score;
  private int question_type_id;
  private int question_level_id;

  private String[] answername;
  private String[] pair;
  private boolean[] is_correct;


}
