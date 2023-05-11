package com.example.Music_play.modelMessage;

import com.example.Music_play.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMessage {
    private Category category;

    private String message;
}
