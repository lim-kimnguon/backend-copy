package com.quiz.quizsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.model.UserPosition;
import com.quiz.quizsystem.repository.UserPositionRepository;

@Service
@Transactional
public class UserPositionService {


  @Autowired
  public UserPositionRepository userPositionRepository;
  
 
  public List<UserPosition> ListUserPositions(){
    return userPositionRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }
  
  public void saveUserPosition(UserPosition userPosition){
    userPositionRepository.save(userPosition);
  }

  public UserPosition getUserPosition(Integer id){
    return userPositionRepository.findById(id).get();
  }
  
  public int DeleteUserPosition(Integer id){
    return userPositionRepository.DeleteUserPosition(id);
  }


}
