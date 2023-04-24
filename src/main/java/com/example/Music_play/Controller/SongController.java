package com.example.Music_play.Controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.example.Music_play.MusicPlayApplication;
import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import com.example.Music_play.repository.SongRepository;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
        String url = song.getLink();
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];
        String publicId = lastPart.substring(0, lastPart.lastIndexOf("."));
//        publicId = "xkg3rhruexvf6gj3kgn8";
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("resource_type","video"));;
            songRepository.delete(song);
            System.out.println(publicId);
            System.out.println(result);
        }
        catch (IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Delete successfully!";
    }
//    public static void main(String[] args) {
//        String url = "https://res.cloudinary.com/dvf7kmois/video/upload/v1682304168/twnfkb26niygg658ek0l.mp3";
////        String[] parts = url.split("/");
////        String lastPart = parts[parts.length - 1];
////        String publicId = lastPart.substring(0, lastPart.lastIndexOf("."));
//        String publicId = url.replace("https://res.cloudinary.com/dvf7kmois/", "");
//        cloudinary.uploader().destroy("publicId",null);
//
//    }
}
