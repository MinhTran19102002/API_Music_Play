package com.example.Music_play.modelDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private long id;

    private String name;

    private String image;

    private String description;
}
