package com.example.Music_play.modelMessage;

import com.example.Music_play.model.Song;
import com.example.Music_play.modelDTO.SongDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongMessage {
    private SongDTO song;

    private List<SongDTO> songs;

    private String message;
}
