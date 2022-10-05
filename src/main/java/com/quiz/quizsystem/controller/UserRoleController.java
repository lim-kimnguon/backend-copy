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
import com.quiz.quizsystem.model.UserRole;
import com.quiz.quizsystem.service.UserRoleService;

@RestController
@RequestMapping("/user/role")
public class UserRoleController {
  @Autowired
  private UserRoleService userRoleService;

  @GetMapping
  public ResponseEntity<List<UserRole>> ListUserRole(){
    List<UserRole> userroles = userRoleService.ListUserRoles();
    return new ResponseEntity<>(userroles,HttpStatus.OK);
  }
  

  @GetMapping("/{id}")
  ResponseEntity<UserRole> ListById(@PathVariable Integer id){
    UserRole userrole = userRoleService.getUserRole(id);
    return new ResponseEntity<>(userrole,HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<?> CreateUserRole(@RequestBody RequestName getName){
    UserRole userRole = new UserRole();
    userRole.setName(getName.getName());

    userRoleService.saveUserRole(userRole);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> UpdateUserRole(@RequestBody RequestName getName, @PathVariable Integer id){
      try{
        UserRole exitRole = userRoleService.getUserRole(id);
        UserRole userRole = new UserRole();

        userRole.setId(id);
        userRole.setName(getName.getName());

        userRoleService.saveUserRole(userRole);
        return new ResponseEntity<>(HttpStatus.OK);
      }catch(NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteUserRole(@PathVariable Integer id){
    try{
      userRoleService.deleteUserRole(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(NoSuchElementException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  
}
