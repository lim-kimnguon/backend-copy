package com.quiz.quizsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangepassword {
  private String oldpassword;
  private String newpassword;
}
