package com.example.Music_play.Controller;


import com.cloudinary.Cloudinary;
import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import com.example.Music_play.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {
    @Autowired
    private SongRepository songRepository;

    private final Cloudinary cloudinary;

    @Autowired
    public SongController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }



    @PostMapping(value = "/create")
    public String createSong(@RequestParam("file") MultipartFile file, @RequestParam String name) throws IOException {
        String fileName = name;
        Map<String, String> options = new HashMap<>();
        options.put("resource_type", "video");
        options.put("format", "mp3");
        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), options);
        String link = result.get("secure_url").toString();
        Song song = new Song();
        song.setLink(link);
        song.setName(fileName);
        try {
            songRepository.save(song);
            return  "You have successfully created a song!";
        }
        catch (Exception e)
        {
            return "You have failed created a song!";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteSong(@RequestParam Long id){
        Song song = songRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Song not exist with id" + id));
        songRepository.delete(song);
        return "Delete successfully!";
    }
}
