package com.quiz.quizsystem.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.domain.AppUserDetails;
import com.quiz.quizsystem.dto.RequestChangepassword;
import com.quiz.quizsystem.dto.RequestEmail;
import com.quiz.quizsystem.dto.RequestUser;
import com.quiz.quizsystem.model.User;
import com.quiz.quizsystem.repository.UserRepository;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final EmailSenderService emailSenderService;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
      EmailSenderService emailSenderService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailSenderService = emailSenderService;
  }

  public List<User> ListUsers() {
    return userRepository.findByStatusFalse(Sort.by(Sort.Order.desc("id")));
  }

  public List<User> listUserbyRoleName(String role_name) {
  
      return userRepository.findUserByRolename(role_name);
    
  }


  public void saveUser(RequestUser requestuser) {

    User user = new User();

    String password = generateRandomPassword(12);
    System.out.println(password);
    user.setFirst_name(requestuser.getFirst_name());
    user.setLast_name(requestuser.getLast_name());
    user.setEmail(requestuser.getEmail());
    user.setPassword(encodedPassword(password));
    user.setGender(requestuser.getGender());
    user.setPhone(requestuser.getPhone());
    user.setRoleId(requestuser.getRoleid());
    user.setPositionId(requestuser.getPositionid());


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime quizdate = LocalDateTime.parse(requestuser.getQuizdate(), formatter);
    user.setQuizdate(quizdate);

    userRepository.save(user);

  }

  public void editUser(RequestUser requestuser, Integer id) {

    User user = new User();

    user.setId(id);
    user.setFirst_name(requestuser.getFirst_name());
    user.setLast_name(requestuser.getLast_name());
    user.setEmail(requestuser.getEmail());
    user.setPassword(requestuser.getPassword());
    user.setGender(requestuser.getGender());
    user.setPhone(requestuser.getPhone());
    user.setRoleId(requestuser.getRoleid());
    user.setPositionId(requestuser.getPositionid());


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime quizdate = LocalDateTime.parse(requestuser.getQuizdate(), formatter);
    user.setQuizdate(quizdate);


    userRepository.save(user);

  }

  public boolean changepassword(String email, RequestChangepassword changpassworddata) {

    User user = this.findUserByEmail(email);
    if (user.getEmail()!=null) {
      if (passwordEncoder.matches(changpassworddata.getOldpassword(), user.getPassword())) {
        user.setPassword(encodedPassword(changpassworddata.getNewpassword()));
        return true;
      } else {
        return false;
      }
    }else{
      return false;
    }

  }


  public User generatenewpassword(RequestEmail requestEmail){
    User user = userRepository.findUserByEmail(requestEmail.getEmail());
    String newpassword = generateRandomPassword(12);
    user.setPassword(encodedPassword(newpassword));
    sendEmailGenerateNewpassword(user.getEmail(),newpassword);
    return user;
  }


  public User resetPassword(String email, String newPassword) {
    User user = userRepository.findUserByEmail(email);
    user.setPassword(encodedPassword(newPassword));
    userRepository.save(user);
    return user;
  }

  public User getUser(Integer id) {
    return userRepository.findById(id).get();
  }
 

  public int deleteUser(Integer id) {
    return userRepository.DeleteUser(id);
  }

  public List<User> finduserbycreated(Date start_date, Date end_date) {
    return userRepository.findUserByCreatedBetween(start_date, end_date);
  }

  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findUserByEmail(email);
    return new AppUserDetails(user);
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

  private String encodedPassword(String password) {
    return passwordEncoder.encode(password);
  }

  public void sendEmailforgotpassword(String email, String token) {
    emailSenderService.sendEmail(email, "Reset Password Form Link", "http://localhost:4200/forgetpassword?token=" + token);
  }
  public void sendEmailGenerateNewpassword(String email,String newpassword) {
    emailSenderService.sendEmail(email,"Account Information","Password: "+newpassword );
  }
}
