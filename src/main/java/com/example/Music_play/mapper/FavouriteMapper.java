package com.example.Music_play.mapper;

import com.example.Music_play.model.Favourite;
import com.example.Music_play.modelDTO.FavouriteDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FavouriteMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "song", target = "song")
    @Mapping(source = "user", target = "user")
    @BeanMapping(ignoreByDefault = true)
    @Named("FavouriteDTO")
    FavouriteDTO getListFavorite(Favourite favourite);

    @Named("getNoticeBoardStatusName")
    @IterableMapping(elementTargetType = FavouriteDTO.class, qualifiedByName = "FavouriteDTO")
    List<FavouriteDTO> getListFavorite(List<Favourite> favourites);
}
