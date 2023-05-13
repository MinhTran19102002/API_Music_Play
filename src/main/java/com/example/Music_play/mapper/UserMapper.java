package com.example.Music_play.mapper;

import com.example.Music_play.model.Category;
import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import com.example.Music_play.modelDTO.CategoryDTO;
import com.example.Music_play.modelDTO.SongDTO;
import com.example.Music_play.modelDTO.UserDTO;
import jdk.jfr.Name;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    @Name("UserDTO")
    UserDTO getListUser(User user);

    List<UserDTO> getListUser(List<User> users);
}
