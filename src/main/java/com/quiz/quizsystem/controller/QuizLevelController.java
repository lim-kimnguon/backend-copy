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

import com.quiz.quizsystem.dto.RequestName;
import com.quiz.quizsystem.model.QuizLevel;
import com.quiz.quizsystem.service.QuizLevelService;

@RestController
@RequestMapping("/quiz/level")
public class QuizLevelController {
  

  @Autowired
  private QuizLevelService quizLevelService;

  @GetMapping
  public ResponseEntity<List<QuizLevel>> ListQuizlevels(){
    List<QuizLevel> quizlevels = quizLevelService.ListAllQuizLevel();
     return new ResponseEntity<>(quizlevels, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuizLevel> ListQuizlevelById(@PathVariable Integer id){
    QuizLevel quizlevel =  quizLevelService.getQuizLevel(id);
    return new ResponseEntity<>(quizlevel,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> CreateQuizLevel(@RequestBody RequestName getName){
    QuizLevel quizLevel = new QuizLevel();
    quizLevel.setName(getName.getName());

    quizLevelService.saveQuizLevel(quizLevel);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> UpdateQuizLevel(@RequestBody RequestName getName, @PathVariable Integer id){
      try{
        QuizLevel exitQuizLevel = quizLevelService.getQuizLevel(id);
        QuizLevel quizLevel = new QuizLevel();

        quizLevel.setId(id);
        quizLevel.setName(getName.getName());

        quizLevelService.saveQuizLevel(quizLevel);
        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteQuizLevel(@PathVariable Integer id){
    try{
      quizLevelService.deleteQuizLevel(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
