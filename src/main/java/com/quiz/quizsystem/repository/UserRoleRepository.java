package com.quiz.quizsystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.quizsystem.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>{
  List<UserRole> findByDeleteFalse(Sort sort);

  @Modifying
  @Query(value ="UPDATE quiz.user_roles set is_delete=true where id= :id",nativeQuery = true)
  int DeleteUserRole(@Param("id") Integer id);
}
