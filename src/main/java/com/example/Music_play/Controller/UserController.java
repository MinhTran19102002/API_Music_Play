package com.example.Music_play.Controller;

import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.model.User;
import com.example.Music_play.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/all")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
    }

    @PostMapping(value = "/create")
    public String createUser(@RequestBody User user){
//        userRepository.findBy()
//        if(user.getPhone() ==)
        try {
            userRepository.save(user);
            return  "You have successfully created a user account!";
        }
        catch (Exception e)
        {
            return "You have failed created a user account!";
        }

        //return  "You have successfully created a user account!";
    }

    @GetMapping(value = "/getuserbyid/{id}")
    public User getuserbyid(@PathVariable long id)
    {
        User user = userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("User not exist with id" + id));
        return user;
    }

    @PutMapping(value = "/updateuserbyid/{id}/")
    public User updateuserbyid(@PathVariable long id, @RequestBody User user)
    {
        User updateUser = userRepository.findById(id).get();
        updateUser.setFirst_name(user.getFirst_name());
        updateUser.setLast_name(user.getLast_name());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        return updateUser;
    }
    @PostMapping(value = "/login")
    public User login(@RequestParam String phone, @RequestParam String password){
        User user = userRepository.Login(phone, password);
        if(user != null)
        {
            return user;
        }
        else {
            return null;
        }
    }

}
