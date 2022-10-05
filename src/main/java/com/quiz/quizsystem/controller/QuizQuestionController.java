package com.quiz.quizsystem.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quiz.quizsystem.dto.RequestMultiQuizQuestion;
import com.quiz.quizsystem.dto.RequestQuizQuestion;
import com.quiz.quizsystem.model.QuizQuestion;
import com.quiz.quizsystem.service.QuizQuestionService;

@RestController
@RequestMapping("/quizquestion")
public class QuizQuestionController {

  @Autowired
  private QuizQuestionService quizquestionService;

  @GetMapping
  public ResponseEntity<List<QuizQuestion>> listData(){
    List<QuizQuestion> quizquestions = quizquestionService.Listall();
    return new ResponseEntity<>(quizquestions,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> AddQuestionToQuiz(@RequestBody RequestQuizQuestion requestQuizQuestion){
    quizquestionService.AddQuestionToQuiz(requestQuizQuestion);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{quiz_id}/{question_id}")
  public ResponseEntity<?> RemoveQuestionFromQuiz(@PathVariable Integer quiz_id,@PathVariable Integer question_id ){
    quizquestionService.DeleteQuestionFromQuiz(quiz_id,question_id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/multiple")
  public ResponseEntity<?>  AddMultipleQuestion(@RequestBody RequestMultiQuizQuestion requestMultiQuizQuestion){
    quizquestionService.addMultiQuestionToQuiz(requestMultiQuizQuestion.getQuiz_id(),requestMultiQuizQuestion.getQuestion_id());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/deletemultiple")
  public  ResponseEntity<?> DeleteMultipleQuestion(@RequestBody RequestMultiQuizQuestion requestMultiQuizQuestion) {
    try{
        quizquestionService.DeleteMutipleQuestionFromQuiz(requestMultiQuizQuestion.getQuiz_id(),requestMultiQuizQuestion.getQuestion_id());
        return new ResponseEntity<>(HttpStatus.OK);
    
    }catch(NoSuchElementException e){
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
