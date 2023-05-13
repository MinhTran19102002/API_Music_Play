package com.example.Music_play.modelMessage;

import com.example.Music_play.modelDTO.FavouriteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteMessage {
    private String message;
    private FavouriteDTO favorite;
    private List<FavouriteDTO> favorites;
}
