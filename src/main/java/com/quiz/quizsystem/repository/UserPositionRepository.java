package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.UserPosition;

@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition,Integer>{
  List<UserPosition> findByDeleteFalse(Sort sort);

  @Modifying
  @Query(value ="UPDATE quiz.user_positions set is_delete=true where id= :id",nativeQuery = true)
  int DeleteUserPosition(@Param("id") Integer id);

 
}
