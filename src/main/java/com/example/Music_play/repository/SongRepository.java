package com.example.Music_play.repository;

import com.example.Music_play.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    //all crud database methods
    @Query("select s from Song s where s.category.id= :category_id")
    List<Song> findByCategory(@Param("category_id") Long category_id);

    @Query("select s from Song s where s.name like %:name%")
    List<Song> GetByName(@Param("name") String name);
}
