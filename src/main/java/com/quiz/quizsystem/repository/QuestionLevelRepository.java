package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.QuestionLevel;

import org.springframework.data.domain.Sort;

@Repository
public interface QuestionLevelRepository extends JpaRepository<QuestionLevel,Integer> {
 

  List<QuestionLevel> findByDeleteFalse(Sort sort);

  @Modifying
  @Query(value ="UPDATE quiz.question_levels set is_delete=true where id= :id",nativeQuery = true)
  int DeleteQuestionLevels(@Param("id") Integer id);

}
