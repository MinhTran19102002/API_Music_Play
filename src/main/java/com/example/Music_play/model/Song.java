package com.example.Music_play.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "author")
    private String author;

    @Column(name = "singer")
    private String singer;

    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;


    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Favourite> favourites;
}
