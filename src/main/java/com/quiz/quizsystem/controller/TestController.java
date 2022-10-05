package com.quiz.quizsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{
  
  @GetMapping
  String test(){
    return "Hello Spring Quiz";
  }


}
