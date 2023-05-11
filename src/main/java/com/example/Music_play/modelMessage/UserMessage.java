package com.example.Music_play.modelMessage;

import com.example.Music_play.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {
    private User user;

    private String message;
}
