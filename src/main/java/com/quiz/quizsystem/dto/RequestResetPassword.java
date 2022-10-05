package com.quiz.quizsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResetPassword {
  private String email;
  private String newpassword;
}
