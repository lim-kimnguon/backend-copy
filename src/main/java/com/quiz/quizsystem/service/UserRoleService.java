package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.UserRole;
import com.quiz.quizsystem.repository.UserRoleRepository;

@Service
@Transactional
public class UserRoleService {
  

  @Autowired
  private UserRoleRepository userRoleRepository;

  public List<UserRole> ListUserRoles(){
    return userRoleRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }

  public void saveUserRole(UserRole userRole){
    userRoleRepository.save(userRole);
  }


  public UserRole getUserRole(Integer id){
    return userRoleRepository.findById(id).get();
  }
  
  public int deleteUserRole(Integer id){
    return userRoleRepository.DeleteUserRole(id);
  }

}
