package com.example.Music_play;

import com.example.Music_play.model.User;
import com.example.Music_play.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicPlayApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MusicPlayApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;


	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setEmail("fafj");
//		user.setFirst_name("Tran");
//		user.setLast_name("Hau");
//		user.setPassword("1234");
//		userRepository.save(user);
	}
}
