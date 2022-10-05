package com.quiz.quizsystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
  private String first_name;
  private String last_name;
  private String email;
  private String password;
  private String gender;
  private String phone;
  private long roleid;
  private long positionid;
  private String quizdate;

}
