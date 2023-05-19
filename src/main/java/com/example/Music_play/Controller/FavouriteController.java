package com.example.Music_play.Controller;

import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.mapper.FavouriteMapper;
import com.example.Music_play.model.Favourite;
import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import com.example.Music_play.modelDTO.FavouriteDTO;
import com.example.Music_play.modelMessage.FavouriteMessage;
import com.example.Music_play.repository.FavouriteReponsitory;
import com.example.Music_play.repository.SongRepository;
import com.example.Music_play.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    @Autowired
    FavouriteReponsitory favouriteReponsitory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    FavouriteMapper favouriteMapper;


    @PostMapping(value = "/find")
    public FavouriteMessage findFavorite(@RequestParam Long songId, @RequestParam Long userId){
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.findFavorite(songId, userId);
        if(favourite != null){
            favouriteDTO = favouriteMapper.getListFavorite(favourite);
            favouriteMessage.setFavorite(favouriteDTO);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
    @PostMapping(value = "/add")
    public FavouriteMessage addFavourite(@RequestParam Long songId, @RequestParam Long userId){
        Song song = songRepository.findById(songId).
                orElseThrow(()-> new ResourceNotFoundException("Song not exist with id" + songId));
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User not exist with id" + userId));
        Favourite favourite = new Favourite();
        favourite.setSong(song);
        favourite.setUser(user);
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        try{
            if(song!= null && user!= null) {
                favouriteReponsitory.save(favourite);
                FavouriteDTO favouriteDTO = favouriteMapper.getListFavorite(favourite);
                favouriteMessage.setMessage("Successful");
                favouriteMessage.setFavorite(favouriteDTO);
            }
        }
        catch (Exception e)
        {
            favouriteMessage.setMessage("Failed");
        }
        return  favouriteMessage;
    }
    @PostMapping(value = "/delete")
    public FavouriteMessage deleteFavorite(@RequestParam Long songId, @RequestParam Long userId){
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.findFavorite(songId, userId);
        if(favourite != null){
            favouriteReponsitory.delete(favourite);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
    @PostMapping(value = "/listByUser")
    public FavouriteMessage listByUser(@RequestParam Long userId){
        System.out.println("userId");
        System.out.println("------------");
        List<Favourite> favourite;
        List<FavouriteDTO>  favouriteDTOs ;
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.listByUser(userId);
        if(favourite != null){
            favouriteDTOs = favouriteMapper.getListFavorite(favourite);
            favouriteMessage.setFavorites(favouriteDTOs);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
}
