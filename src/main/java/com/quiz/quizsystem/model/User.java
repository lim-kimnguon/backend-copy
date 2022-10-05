package com.quiz.quizsystem.model;

import java.time.LocalDateTime;
import java.util.Collection;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "quiz")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String first_name ;
  private String last_name ;
  private String email;


  private String password;
  private String gender;
  private String phone;
  private boolean status=false;

  @Column(name = "user_role_id")
  @JsonIgnore
  private long roleId;

  @Column(name = "user_position_id")
  @JsonIgnore
  private long positionId;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_role_id", referencedColumnName = "id", insertable = false, updatable = false)
  private UserRole userRole;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_position_id", referencedColumnName = "id", insertable = false, updatable = false)
  private UserPosition userPosition;


  @Column(name = "quizdate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime quizdate;
  

  @Column(name = "modified")
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime modified = LocalDateTime.now();

  @Column(name = "created",updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime created = LocalDateTime.now();


  
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy("id desc")
	@JoinTable(
			name = "user_quizzes",
      schema = "quiz",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "quiz_id", referencedColumnName = "id"))
  @JsonIgnoreProperties("users")    
	private Collection<Quiz> quizs;


}
