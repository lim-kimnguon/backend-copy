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
import com.quiz.quizsystem.model.UserPosition;
import com.quiz.quizsystem.service.UserPositionService;

@RestController
@RequestMapping("/user/position")
public class UserPositionController {
  @Autowired
  private UserPositionService userPositionService;

  @GetMapping
  public ResponseEntity<List<UserPosition>> List(){
    List<UserPosition> userpositions = userPositionService.ListUserPositions();
    return new ResponseEntity<>(userpositions,HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public ResponseEntity<UserPosition> ListById(@PathVariable Integer id){
    UserPosition userposition = userPositionService.getUserPosition(id);
    return new ResponseEntity<>(userposition,HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<?> CreateUserPosition(@RequestBody RequestName getName){
    UserPosition userPosition = new UserPosition();    
    userPosition.setName(getName.getName());

    userPositionService.saveUserPosition(userPosition);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> UpdateUserPosition(@RequestBody RequestName getName, @PathVariable Integer id){
      try{
        UserPosition exiPosition = userPositionService.getUserPosition(id);
        UserPosition userPosition = new UserPosition();

        userPosition.setId(id);
        userPosition.setName(getName.getName());

        userPositionService.saveUserPosition(userPosition);
        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteUserPosition(@PathVariable Integer id){
    try{
      
      userPositionService.DeleteUserPosition(id);

      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


  
}
