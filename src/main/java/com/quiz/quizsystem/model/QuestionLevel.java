package com.quiz.quizsystem.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "question_levels", schema = "quiz")
public class QuestionLevel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Column(name = "modified")
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime modified=LocalDateTime.now(); 

  @Column(name = "created", updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime created = LocalDateTime.now(); 

  @Column(name="is_delete")
  private boolean delete=false;


}
