package io.github.jasmin.controller;

import io.github.jasmin.model.User;
import io.github.jasmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userid}")
    public User getUserByUserid(@PathVariable String userid){
        return userService.getUserByUserId(userid);
    }

    @PostMapping("")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PutMapping("/{userid}")
    public void modifyUser(@PathVariable String userid, @RequestBody User user){
        userService.modifyUser(userid, user);
    }

    @DeleteMapping("/{userid}")
    public void removeUser(@PathVariable String userid){
        userService.removeUser(userid);
    }
}
