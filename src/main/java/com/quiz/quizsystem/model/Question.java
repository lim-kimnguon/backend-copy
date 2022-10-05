package com.quiz.quizsystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "questions", schema = "quiz")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  private String name;

  private int timeout = 60;

  private int score = 5;

  private boolean status = false;

  @JsonIgnore
  private int question_level_id;
  @JsonIgnore
  private int question_type_id;

  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId("question_type_id")
  @JoinColumn(name = "question_type_id", referencedColumnName = "id", insertable = false, updatable = false)
  private QuestionType questionType;

  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId("question_level_id")
  @JoinColumn(name = "question_level_id", referencedColumnName = "id", insertable = false, updatable = false)
  private QuestionLevel questionLevel;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy("id desc")
  @JoinColumn(name = "question_id", referencedColumnName = "id", insertable = false, updatable = false)
  private List<Answer> answer ;

  @Column(name = "modified")
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime modified = LocalDateTime.now();

  @Column(name = "created", updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "is_delete")
  private boolean delete = false;

 

  @ManyToMany(mappedBy = "questions")
  @JsonIgnoreProperties("questions")
  private Collection<Quiz> quiz = new HashSet<>();
 

  

}
