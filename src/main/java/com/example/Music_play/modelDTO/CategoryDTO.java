package com.example.Music_play.modelDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private long id;

    private String name;

    private String image;

    private String description;
}
