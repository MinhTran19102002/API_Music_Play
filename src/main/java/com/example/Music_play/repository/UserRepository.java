package com.example.Music_play.repository;

import com.example.Music_play.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    //all crud database methods
}
