package com.quiz.quizsystem.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "quizzes",schema = "quiz")
public class Quiz {
  

  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String description;
  private int breaktime=10*60;

  @JsonIgnore
  private int quiz_level_id;
  
  @Column(name="status")
  private boolean status=false;

  @Column(name = "modified")
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime modified=LocalDateTime.now(); 

  @Column(name = "created", updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime created = LocalDateTime.now(); 

  @Column(name="is_delete")
  private boolean delete=false;

  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId("quiz_level_id")
  @JoinColumn(name="quiz_level_id",referencedColumnName = "id", insertable = false, updatable = false)
  private QuizLevel quizLevel;


  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy("id desc")
	@JoinTable(
			name = "quiz_questions",
      schema = "quiz",
			joinColumns = @JoinColumn(
		            name = "quiz_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "question_id", referencedColumnName = "id"))
  @JsonIgnoreProperties("quizs")
	private Collection<Question> questions;


  @ManyToMany(mappedBy = "quizs")
  @JsonIgnoreProperties("quizs")
  private Collection<User> user = new HashSet<>();
 


}
