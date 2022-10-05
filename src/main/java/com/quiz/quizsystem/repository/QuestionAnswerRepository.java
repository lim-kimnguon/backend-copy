package com.quiz.quizsystem.repository;

import com.quiz.quizsystem.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {
}
