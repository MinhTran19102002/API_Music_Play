package com.example.Music_play.Controller;

import com.example.Music_play.exception.ResourceNotFoundException;
import com.example.Music_play.mapper.UserMapper;
import com.example.Music_play.model.Song;
import com.example.Music_play.model.User;
import com.example.Music_play.modelDTO.UserDTO;
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

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/all")
    public UserMessage getAllUser(){
        List<UserDTO> userDTOS = userMapper.getListUser(userRepository.findAll());
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTOS(userDTOS);
        userMessage.setMessage("Successful");
        System.out.println("Successful");
        return userMessage;
    }

    @PostMapping(value = "/register")
    public UserMessage register(@RequestBody User user){
        user.setRole("user");
        UserMessage userMessage = new UserMessage();
        try {
            UserDTO userDTO = userMapper.getListUser(userRepository.save(user));
            userMessage.setUserDTO(userDTO);
            userMessage.setMessage("You have successfully created a user account!");
            return  userMessage;
        }
        catch (Exception e)
        {
            userMessage.setUserDTO(null);
            userMessage.setMessage("You have failed to create a user account!");
            return userMessage;
        }
    }

    @GetMapping(value = "/getuserbyid/{id}")
    public UserMessage getuserbyid(@PathVariable long id)
    {
        User user = userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("User not exist with id" + id));
        UserDTO userDTO = userMapper.getListUser(user);
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTO(userDTO);
        return userMessage;
    }

    @PutMapping(value = "/update/{id}")
    public UserMessage updateuserbyid(@PathVariable long id, @RequestBody User user)
    {
        User updateUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Song not exist with id" + id));;
        updateUser.setFirst_name(user.getFirst_name());
        updateUser.setLast_name(user.getLast_name());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        userRepository.save(updateUser);
        UserDTO userDTO = userMapper.getListUser(user);
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTO(userDTO);
        userMessage.setMessage("Successful");
        return userMessage;
    }
    @PostMapping(value = "/login")
    public UserMessage login(@RequestParam String phone, @RequestParam String password){
        System.out.println(phone);
        User user = userRepository.Login(phone, password);
        UserDTO userDTO = userMapper.getListUser(user);
        UserMessage userLogin = new UserMessage();
        if(user != null)
        {
            userLogin.setMessage("Login is successful!");
            userLogin.setUserDTO(userDTO);
            return userLogin;
        }
        else {
            userLogin.setMessage("Login is failed!");
            userLogin.setUserDTO(null);
            return userLogin;
        }
    }

    @PostMapping(value = "/delete")
    public UserMessage deleteUser(@RequestParam Long id){
        UserMessage userMessage = new UserMessage();
        User user = userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Song not exist with id" + id));
        if(user != null)
        {
            userRepository.delete(user);
            return userMessage;
        }
        else {
            userMessage.setMessage("Failed");
            return userMessage;
        }
    }
}
