package io.xt.controller;

import io.xt.dto.AddUserDTO;
import io.xt.pojo.User;
import io.xt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public void addUser(@RequestBody AddUserDTO addUserDTO){
        userService.addUser(addUserDTO);
    }
}
