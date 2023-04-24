package com.example.Music_play.repository;

import com.example.Music_play.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    //all crud database methods

    @Query("select u from User u where u.phone = :phone and u.password= :password")
    User Login(@Param("phone") String phone, @Param("password")  String password);
}
