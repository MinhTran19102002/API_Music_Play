package com.example.Music_play.modelDTO;

import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDTO {
    private long id;

    private SongDTO song;

    private UserDTO user;
}
