package com.example.Music_play.Controller;

import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.model.User;
import com.example.Music_play.modelMessage.UserMessage;
import com.example.Music_play.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/register")
    public UserMessage register(@RequestBody User user){
        UserMessage userMessage = new UserMessage();
        try {
            userRepository.save(user);
            userMessage.setUser(user);
            userMessage.setMessage("You have successfully created a user account!");
            return  userMessage;
        }
        catch (Exception e)
        {
            userMessage.setUser(null);
            userMessage.setMessage("You have failed to create a user account!");
            return userMessage;
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
    public UserMessage login(@RequestParam String phone, @RequestParam String password){
        System.out.println(phone);
        User user = userRepository.Login(phone, password);
        UserMessage userLogin = new UserMessage();
        if(user != null)
        {
            userLogin.setMessage("Login is successful!");
            userLogin.setUser(user);
            return userLogin;
        }
        else {
            userLogin.setMessage("Login is failed!");
            userLogin.setUser(null);
            return userLogin;
        }
    }

}
