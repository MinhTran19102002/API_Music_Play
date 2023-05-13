package com.example.Music_play.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;

    private  String phone;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private String role;
}
