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

import com.quiz.quizsystem.dto.RequestAnswer;
import com.quiz.quizsystem.model.Answer;
import com.quiz.quizsystem.service.AnswerService;

@RestController
@RequestMapping("/question/answer")
public class AnswerController {
  

  @Autowired
  private AnswerService answerService;



  @GetMapping
  ResponseEntity<List<Answer>> ListAsnwers(){
    List<Answer> answers = answerService.ListAllAnswer();
    return new ResponseEntity<>(answers,HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<Answer> ListAsnwerById(@PathVariable Integer id){
    Answer answer = answerService.getAnswer(id);
    return new ResponseEntity<>(answer,HttpStatus.OK);
  }

  @GetMapping("/question/{id}")
  ResponseEntity<List<Answer>> ListByQuestionId(@PathVariable Integer id){
    List<Answer> answers = answerService.getAnswerByQuestionId(id);
    return new ResponseEntity<>(answers,HttpStatus.OK);
  
  }

  @PostMapping
  ResponseEntity<?> CreateAnswer(@RequestBody RequestAnswer requestAnswer){
    System.out.println(requestAnswer.getName());
    Answer answer = new Answer();

    answer.setName(requestAnswer.getName());
    answer.setPair(requestAnswer.getPair());
    answer.setCorrect(requestAnswer.getIs_correct());
    answer.setQuestion(requestAnswer.getQuestion_id());

    answerService.saveAnswer(answer);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> UpdateQuestionLevel(@RequestBody RequestAnswer requestAnswer, @PathVariable Integer id){
      try{
        Answer Exitaswer = Answer.getAnswer(id);
        Answer answerUpdate = new Answer();

        answerUpdate.setId(id);
        answerUpdate.setName(requestAnswer.getName());
        answerUpdate.setPair(requestAnswer.getPair());
        answerUpdate.setCorrect(requestAnswer.getIs_correct());
        answerUpdate.setQuestion(requestAnswer.getQuestion_id());


        answerService.saveAnswer(answerUpdate);

        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteAnswer(@PathVariable Integer id){
    try{
      var deleteAswer = answerService.deleteAswer(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }



}
