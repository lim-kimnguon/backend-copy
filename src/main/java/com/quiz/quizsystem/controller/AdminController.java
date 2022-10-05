package com.quiz.quizsystem.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizsystem.dto.RequestUser;
import com.quiz.quizsystem.model.User;
import com.quiz.quizsystem.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class AdminController {

  @Autowired
  private UserService userService;

  @PostMapping
  void CreateUser(@RequestBody RequestUser requestuser) {
    userService.saveUser(requestuser);
  }
  
  @PutMapping("/{id}")
  ResponseEntity<?> UpdateUser(@RequestBody RequestUser requestuser, @PathVariable Integer id) {
    try {
      User exiPosition = userService.getUser(id);
      userService.editUser(requestuser, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

}
