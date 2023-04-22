package com.example.Music_play.repository;

import com.example.Music_play.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
    //all crud database methods
}
