package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
  
  List<Answer> findByDeleteFalse(Sort sort);

  List<Answer> findAllByQuestionIsAndDeleteFalse(Integer questionid,Sort sort);

  @Modifying
  @Query(value ="UPDATE quiz.answers set is_delete=true where id= :id",nativeQuery = true)
  int DeleteAnswerById(@Param("id") Integer id);



  String DeleteAnswerInName = "DELETE FROM quiz.answers "+
	" WHERE (quiz.answers.question_id= :qestion_id AND  quiz.answers.is_correct != false) AND quiz.answers.name in :name ";
  @Modifying
  @Query(value = DeleteAnswerInName , nativeQuery = true)
  int DeleteMultipleAnswer(@Param("qestion_id") int qestion_id, @Param("name") String[] name);




}
