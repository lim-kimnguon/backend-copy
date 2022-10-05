package com.quiz.quizsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserQuizPublic {
  int quiz_id;
  int user_id;
  boolean ispublic;
}
