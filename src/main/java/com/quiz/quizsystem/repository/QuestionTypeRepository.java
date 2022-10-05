package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType,Integer> {
 
  // @Query(value ="SELECT * from quiz.question_types where is_delete=false",nativeQuery = true)
  // List<QuestionType> findQuestionTypes();

  List<QuestionType> findByDeleteFalse();

  @Modifying
  @Query(value ="UPDATE quiz.question_types set is_delete=true where id= :id",nativeQuery = true)
  int DeleteQuestionType(@Param("id") Integer id);
  
}