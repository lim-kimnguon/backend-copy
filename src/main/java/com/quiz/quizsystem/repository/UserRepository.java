package com.quiz.quizsystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.User;


@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{
  
  List<User> findByStatusFalse(Sort sort);

  User findUserByEmail(String email);
  
   static String querywithcreated= "select * from quiz.users "+
                                   "where status = false AND created between :start_date AND :end_date ";
   @Query(value=querywithcreated,nativeQuery = true)
   List<User> findUserByCreatedBetween(@Param("start_date") Date start_date,@Param("end_date") Date end_date);


   static String querySelectUser= "select * from quiz.users as us "+
   " join quiz.user_roles as usr on us.user_role_id = usr.id "+
   " where usr.name = :role_name and us.status = false "+
   " order by us.id DESC; ";                    
   @Query(value=querySelectUser,nativeQuery = true)
   List<User> findUserByRolename(@Param("role_name") String role_name);


  @Modifying
  @Query(value ="UPDATE quiz.users set is_delete=true where id= :id",nativeQuery = true)
  int DeleteUser(@Param("id") Integer id);

  


}
