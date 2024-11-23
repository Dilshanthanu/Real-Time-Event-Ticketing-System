package com.example.Real_Time.Event.Ticketing.System.Repo;

import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<user,Integer> {

//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM user u WHERE u.email = :email")
//    boolean existsByEmail(@Param("email") user email);
//
//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM user u WHERE u.password = :password")
//    boolean existsByPassword(@Param("password") user password);
    user findByEmail(String email);
boolean existsByEmail(String email);
}
