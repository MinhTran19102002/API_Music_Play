package com.example.Music_play.repository;

import com.example.Music_play.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteReponsitory extends JpaRepository<Favourite, Long> {
    //all crud database methods
}
