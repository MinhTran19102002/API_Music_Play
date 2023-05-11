package com.example.Music_play.Controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.mapper.SongMapper;
import com.example.Music_play.model.Category;
import com.example.Music_play.model.Song;
import com.example.Music_play.modelDTO.SongDTO;
import com.example.Music_play.modelMessage.SongMessage;
import com.example.Music_play.repository.CategoryRepository;
import com.example.Music_play.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SongMapper songMapper;

    private final Cloudinary cloudinary;

    @Autowired
    public SongController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping(value = "/create")
    public String createSong(@RequestParam("file") MultipartFile file,@RequestParam("image") MultipartFile image, @RequestParam String name,@RequestParam String author, @RequestParam String singer, @RequestParam Long category_id) throws IOException {
        String fileName = name;
        String link ="";
        String linkImage = "";

        //upload file ảnh
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            linkImage = uploadResult.get("secure_url").toString();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        //upload file nhạc mp3
        try {
            Map<String, String> options = new HashMap<>();
            options.put("resource_type", "video");
            options.put("format", "mp3");
            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), options);
            link = result.get("secure_url").toString();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //
        Category category = categoryRepository.getById(category_id);
        //
        Song song = new Song();
        song.setImage(linkImage);
        song.setLink(link);
        song.setSinger(singer);
        song.setAuthor(author);
        song.setName(fileName);
        song.setCategory(category);
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

    @PostMapping(value = "/GetId")
    public SongMessage GetById(@RequestParam Long id){
        Optional<Song> song = songRepository.findById(id);
        System.out.println("--------------");
        System.out.println(id);
        SongMessage songMessage = new SongMessage();
        if(!song.isEmpty())
        {
            Song song1 = song.orElse(new Song());
            SongDTO songDTO = songMapper.getListSong(song1);
            songMessage.setMessage("Successfully");
            songMessage.setSong(songDTO);
        }
        else {
            songMessage.setMessage("Fail");
        }
        System.out.println(songMessage.getSong().getName());
        return songMessage;
    }

    @PostMapping(value = "/SongCategory")
    public SongMessage SongCategory(@RequestParam Long category_id){
        List<Song> songs = songRepository.findByCategory(category_id);
        List<SongDTO> songDTOS = songMapper.getListSong(songs);

        SongMessage songMessage = new SongMessage();
        if (songDTOS.isEmpty()) {
            songMessage.setMessage("Fail");
            songMessage.setSongs(null);
        }
        else {
            songMessage.setMessage("Successfully");
            songMessage.setSongs(songDTOS);
        }

        return songMessage;
    }
}
