package com.quiz.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Integer> {

  @Modifying
  @Query(value = "delete from quiz.quiz_questions where quiz_id=:quiz_id and question_id=:question_id", nativeQuery = true)
  int DeleteQuestionFromQuiz(@Param("quiz_id") Integer quiz_id, @Param("question_id") Integer question_id);

  @Modifying
  @Query(value = "delete from quiz.quiz_questions where quiz_id=:quiz_id and question_id in :question_id", nativeQuery = true)
  int DeleteMutipleQuestionFromQuiz(@Param("quiz_id") int quiz_id, @Param("question_id") int[] question_id);

}
