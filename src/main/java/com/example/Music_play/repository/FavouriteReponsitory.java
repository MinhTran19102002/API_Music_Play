package com.example.Music_play.repository;

import com.example.Music_play.model.Favourite;
import com.example.Music_play.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteReponsitory extends JpaRepository<Favourite, Long> {
    //all crud database methods
    @Query("select f from Favourite f where f.song.id = :songId and f.user.id= :userId")
    Favourite findFavorite(@Param("songId") Long songId, @Param("userId")  Long userId);

    @Query("select f from Favourite f where f.user.id= :userId")
    List<Favourite> listByUser(@Param("userId")  Long userId);
}
