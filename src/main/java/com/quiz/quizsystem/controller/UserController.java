package com.quiz.quizsystem.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizsystem.domain.AppUserDetails;
import com.quiz.quizsystem.dto.RequestChangepassword;
import com.quiz.quizsystem.dto.RequestEmail;
import com.quiz.quizsystem.dto.RequestReport;
import com.quiz.quizsystem.dto.RequestResetPassword;
import com.quiz.quizsystem.dto.RequestUser;
import com.quiz.quizsystem.dto.RequestUserLogin;
import com.quiz.quizsystem.model.User;
import com.quiz.quizsystem.service.UserService;
import com.quiz.quizsystem.util.JwtTokenProvider;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  // auth
  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody RequestUserLogin userlogin) throws Exception {

    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(userlogin.getEmail(), userlogin.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }

    User user = userService.findUserByEmail(userlogin.getEmail());
    AppUserDetails appUserDetails = new AppUserDetails(user);
    HttpHeaders jwtHeader = getJwtHeader(appUserDetails);
    return new ResponseEntity<>(user, jwtHeader, OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> ListUsers() {
    List<User> users = userService.ListUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/userrole/{user_role}")
  public ResponseEntity<List<User>> ListCandidates(@PathVariable String user_role) {
     List<User> users = userService.listUserbyRoleName(user_role);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PostMapping("/report")
  List<User> ListUserBettenCreated(@RequestBody RequestReport report) {
    return userService.finduserbycreated(report.getStart(), report.getEnd());
  }

  @GetMapping("/{id}")
  User ListByUserId(@PathVariable Integer id) {
    return userService.getUser(id);
  }

  @PostMapping
  void CreateUser(@RequestBody RequestUser requestuser) {
    userService.saveUser(requestuser);
  }

  @PostMapping("/forgotpassword")
  ResponseEntity<User> Forgotpassword(@RequestBody RequestEmail requestEmail) {
    String email = requestEmail.getEmail();
    String token = jwtTokenProvider.generateJwtToken(email, 5);
    HttpHeaders jwtHeader = getJwtHeader(token);

    User user = userService.findUserByEmail(email);
    if (user.getEmail() != null) {
      userService.sendEmailforgotpassword(email, token);
    }

    return new ResponseEntity<>(user, jwtHeader, HttpStatus.OK);
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

  @PutMapping("/changepassword/{email}")
  ResponseEntity<?> ChangePassword(@RequestBody RequestChangepassword changepasswordData, @PathVariable String email) {
    try {
      if (userService.changepassword(email, changepasswordData)) {
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/resetpassword")
  ResponseEntity<User> Resetpassword(@RequestBody RequestResetPassword requestResetPassword) {
    System.out.println(requestResetPassword.getEmail() + requestResetPassword.getNewpassword());
    try {
      User user = userService.resetPassword(requestResetPassword.getEmail(), requestResetPassword.getNewpassword());
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/generatenewpassword")
  ResponseEntity<User> generatenewpassword(@RequestBody RequestEmail requestEmail) {
    try {
      User user = userService.generatenewpassword(requestEmail);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> DeleteUser(@PathVariable Integer id) {
    try {

      userService.deleteUser(id);

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public static String generateRandomPassword(int len) {
    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
  }

  private HttpHeaders getJwtHeader(String token) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Jwt-Token", token);
    return headers;
  }

  private HttpHeaders getJwtHeader(AppUserDetails appUserDetails) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Jwt-Token", jwtTokenProvider.generateJwtToken(appUserDetails));
    return headers;
  }

  private void authenticate(String email, String password) {
    System.out.println(email + password);
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
  }



}
