package com.example.Music_play.Controller;

import com.example.Music_play.repository.FavouriteReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favourite")
public class Favourite {
    @Autowired
    FavouriteReponsitory favouriteReponsitory;
}
