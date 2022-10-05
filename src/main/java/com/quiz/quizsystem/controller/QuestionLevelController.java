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
import com.quiz.quizsystem.model.QuestionLevel;
import com.quiz.quizsystem.service.QuestionLevelService;

@RestController
@RequestMapping("/question/level")
public class QuestionLevelController {
  
  @Autowired
  private QuestionLevelService questionLevelService;


  @GetMapping
  ResponseEntity<List<QuestionLevel>> List(){
    List<QuestionLevel> questionlevels = questionLevelService.ListAllQuestionLevel();
    return new ResponseEntity<>(questionlevels,HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<QuestionLevel> ListById(@PathVariable Integer id){
    QuestionLevel questionLevel = questionLevelService.getQuestionLevel(id);
    return new ResponseEntity<>(questionLevel,HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<?> CreateQuestionLevel(@RequestBody RequestName getName){
    QuestionLevel questionLevel = new QuestionLevel();
    questionLevel.setName(getName.getName());
    questionLevelService.saveQuestionLevel(questionLevel);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> UpdateQuestionLevel(@RequestBody RequestName getName, @PathVariable Integer id){
      try{
        QuestionLevel exitQuestionLevel = questionLevelService.getQuestionLevel(id);
        QuestionLevel questionLevel = new QuestionLevel();
        questionLevel.setId(id);
        questionLevel.setName(getName.getName());
        questionLevelService.saveQuestionLevel(questionLevel);
        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteQuestionLevel(@PathVariable Integer id){
    try{
      questionLevelService.deleteQuestionLevel(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


}
