package com.example.Music_play.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private long id;

    private String name;

    private String author;

    private String singer;

    private String link;

    private String image;

    private CategoryDTO category;
}
