package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{


  List<Question> findByDeleteFalse(Sort sort);

  List<Question> findByQuizId(Integer id);


  static String querySelectQuestionNotInQuizId= "select q.*"+
  " from quiz.questions as q "+
  " where is_delete=false and q.id NOT IN ( select q.id"+
  " from quiz.questions as q "+
  " Left Join quiz.quiz_questions as qq on q.id = qq.question_id "+
  " left Join quiz.quizzes as qu on qu.id = qq.quiz_id "+
  "where qu.id = :quiz_id and q.is_delete=false )";
  @Query(value=querySelectQuestionNotInQuizId,nativeQuery = true)
  List<Question> findAllQuestionNotInQuiz(@Param("quiz_id") Integer quiz_id);

 
  @Modifying
  @Query(value ="UPDATE quiz.questions set is_delete=true where id= :id",nativeQuery = true)
  int DeleteQuestion(@Param("id") Integer id);

 
  
}
