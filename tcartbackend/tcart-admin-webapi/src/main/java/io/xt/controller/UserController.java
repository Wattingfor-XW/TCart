package io.xt.controller;

import com.alibaba.fastjson.JSON;
import io.xt.dto.UserAddDTO;
import io.xt.dto.LoginIofo;
import io.xt.dto.UserUpdateDTO;
import io.xt.exception.BackendClientException;
import io.xt.pojo.User;
import io.xt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;

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
    @GetMapping("/getCurrentUserInfo")
    public User getCurrentUserInfo(@RequestAttribute Long userId){
        User currentUser = userService.getUserById(userId);
        return currentUser;
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody UserAddDTO userAddDTO){
        userService.addUser(userAddDTO);
    }
    @GetMapping("/login")
    public String login(String username,String password) throws BackendClientException {
        User byUsername = userService.getByUsername(username);
        if(byUsername==null){
            throw new BackendClientException("账户不存在");

        }else{
            if(!byUsername.getEncryptedPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                throw new BackendClientException("密码错误");
            }
        }
        LoginIofo loginIofo = new LoginIofo(byUsername.getUserId(),byUsername.getUsername(), byUsername.getRoles(), new Date());
        String loginIofoStr = JSON.toJSONString(loginIofo);
        //todo encrypt toketr
        String token = Base64.getEncoder().encodeToString(loginIofoStr.getBytes());
        return token;
    }

    @PostMapping("/update")
    public void update(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.update(userUpdateDTO);
    }
}
