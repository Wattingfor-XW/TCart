package io.xt.controller;

import io.xt.dto.AddUserDTO;
import io.xt.exception.BackendClientException;
import io.xt.pojo.User;
import io.xt.service.UserService;
import org.apache.tomcat.jni.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam Long userId){
        User user = userService.getUserById(userId);
        return user;
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody AddUserDTO addUserDTO){
        userService.addUser(addUserDTO);
    }
    @GetMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("userpassword") String password) throws BackendClientException {
        User byUsername = userService.getByUsername(username);
        if(byUsername!=null){
            if(!byUsername.getEncryptedPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                throw new BackendClientException("密码错误");
            }
        }else{
        throw new BackendClientException("账户不存在");
        }
        return "token";
    }
}