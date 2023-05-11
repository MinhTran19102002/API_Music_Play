package com.example.Music_play.mapper;

import com.example.Music_play.model.Category;
import com.example.Music_play.model.Song;
import com.example.Music_play.modelDTO.CategoryDTO;
import com.example.Music_play.modelDTO.SongDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    @Named("CategoryDTO")
    CategoryDTO getListCategory(Category category);

    @IterableMapping(elementTargetType = CategoryDTO.class, qualifiedByName = "CategoryDTO")
    List<CategoryDTO> getListCategory(List<Category> categories);
}
