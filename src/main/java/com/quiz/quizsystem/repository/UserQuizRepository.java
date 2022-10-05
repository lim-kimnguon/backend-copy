package com.quiz.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.UserQuiz;

@Repository
public interface UserQuizRepository  extends JpaRepository<UserQuiz,Integer>{
 
  @Modifying
  @Query(value = "delete from quiz.user_quizzes where quiz_id=:quiz_id and user_id=:user_id", nativeQuery = true)
  int DeleteQuizFromUserQuiz(@Param("quiz_id") Integer quiz_id, @Param("user_id") Integer user_id);


  @Modifying
  @Query(value = "delete from quiz.user_quizzes where user_id=:user_id and quiz_id in :quiz_id ", nativeQuery = true)
  int DeleteMultipleQuizFromUserQuiz(@Param("user_id") int user_id, @Param("quiz_id") int[] quiz_id);

}
