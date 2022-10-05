package com.quiz.quizsystem.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.Quiz;

@Repository
@Transactional
public interface QuizRepository extends JpaRepository<Quiz,Integer> {


  List<Quiz> findByDeleteFalse(Sort sort);

  Quiz findByIdIs(Integer id);

  List<Quiz> findByUserId(Integer id);

  static String querySelectQuizNotyetAssigntoUser= " select q.* "+
  " from quiz.quizzes as q "+
  "  where is_delete=false and q.id NOT IN ( select q.id "+
  " from quiz.quizzes as q "+
  " Left Join quiz.user_quizzes as uq on q.id = uq.quiz_id "+
  " left Join quiz.users as u on u.id = uq.user_id "+
  " where u.id = :user_id and q.is_delete=false ) ";
  @Query(value=querySelectQuizNotyetAssigntoUser,nativeQuery = true)
  List<Quiz> findAllQuizNotYetAssigntoUser(@Param("user_id") Integer user_id);

  @Modifying
  @Query(value ="UPDATE quiz.quizzes set is_delete=true where id= :id",nativeQuery = true)
  int DeleteQuiz(@Param("id") Integer id);



}
