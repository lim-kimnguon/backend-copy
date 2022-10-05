package com.quiz.quizsystem.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizsystem.dto.RequestQuiz;
import com.quiz.quizsystem.model.Quiz;
import com.quiz.quizsystem.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
  
  @Autowired
  private QuizService quizService;

  @GetMapping
  public ResponseEntity<List<Quiz>> ListQuizzes(){
    List<Quiz> quizzes = quizService.listAllQuizs();
    return new ResponseEntity<>(quizzes,HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Quiz> ListQuizById(@PathVariable Integer id){
    Quiz quiz = quizService.getQuiz(id);
    return new ResponseEntity<>(quiz,HttpStatus.OK);
  }

  @GetMapping("/in/{userid}")
  public ResponseEntity<List<Quiz>> ListQuestionByQuizId(@PathVariable Integer userid){
    List<Quiz> quizzes = quizService.getQuizByUserId(userid);
    return new ResponseEntity<>(quizzes,HttpStatus.OK);
  }

  @GetMapping("/notin/{userid}")
  public ResponseEntity<List<Quiz>> ListQuestionNoInQuizId(@PathVariable Integer userid){
    List<Quiz> quizzes = quizService.getQuiznotyetAssigntoUser(userid);
    return new ResponseEntity<>(quizzes,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> Create(@RequestBody RequestQuiz requestQuiz){
    Quiz quiz = new Quiz();

    quiz.setName(requestQuiz.getName());
    quiz.setDescription(requestQuiz.getDescription());
    quiz.setQuiz_level_id(requestQuiz.getQuiz_level_id());
    quiz.setBreaktime(requestQuiz.getBreaktime());
   
    quizService.saveQuiz(quiz);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> Update(@RequestBody RequestQuiz requestQuiz, @PathVariable Integer id){
      try{
        Quiz exitQuiz = quizService.getQuiz(id);
        Quiz quiz = new Quiz();
       
        quiz.setId(id);
    
        quiz.setName(requestQuiz.getName());
        quiz.setDescription(requestQuiz.getDescription());
        quiz.setQuiz_level_id(requestQuiz.getQuiz_level_id());
        quiz.setBreaktime(requestQuiz.getBreaktime());
      
        quizService.saveQuiz(quiz);
        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id){
    try{
      quizService.deleteQuiz(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
