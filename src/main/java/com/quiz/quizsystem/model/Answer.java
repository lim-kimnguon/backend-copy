package com.quiz.quizsystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "answers",schema = "quiz")
public class Answer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String pair="";

  @Column(name = "is_delete")
  private boolean delete=false;

  @Column(name = "is_correct")
  private boolean correct;

  @Column(name = "question_id")
  private int question;

  public static Answer getAnswer(Integer id2) {
    return null;
  }

  @Column(name = "modified",insertable = true, updatable = true)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime modified=LocalDateTime.now(); 

  @Column(name = "created",insertable = true , updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime created = LocalDateTime.now(); 

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id")
  private User user;

}
