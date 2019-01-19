package io.xt.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.xt.dto.UserAddDTO;
import io.xt.dto.LoginIofo;
import io.xt.dto.UserListDTO;
import io.xt.dto.UserUpdateDTO;
import io.xt.exception.BackendClientException;
import io.xt.pojo.User;
import io.xt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.xml.bind.DatatypeConverter;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String sendAddress;

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
        String token = Base64.getEncoder().encodeToString(loginIofoStr.getBytes());
        return token;
    }

    @PostMapping("/update")
    public void update(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.update(userUpdateDTO);
    }

    @GetMapping("/getUserWithPage")
    public PageInfo<User> getUserWithPage(@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageInfo<User> usersWithPage = userService.getUsersWithPage(pageNum);
        return usersWithPage;
    }

    @PostMapping("/batchDelect")
    public void batchDelect(@RequestBody Long [] userIds){
        for (Long  userId : userIds){
         userService.batchDelect(userId);
        }
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestParam @Email String email,@RequestParam String password){
        userService.changeUserPasswordByEmail(email,password);
    }

    @GetMapping("/resetPassword")
    public void resetPassword(@RequestParam String username,@RequestParam @Email String email) throws BackendClientException {
        User byUsername = userService.getByUsername(username);
        String email1 = byUsername.getEmail();
        if(email1.equals(email)){
            SecureRandom secureRandom = new SecureRandom();
            byte[] bytes = secureRandom.generateSeed(3);
            String code = DatatypeConverter.printHexBinary(bytes);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendAddress);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("TCart Email Verify Code");
            simpleMailMessage.setText(code);
            javaMailSender.send(simpleMailMessage);
            redisTemplate.opsForValue().set(email, code, 10*60, TimeUnit.SECONDS);
        }else{
            throw new BackendClientException("账户与邮箱不匹配");
        }
    }
    @GetMapping("/verifyCode")
    public void verifyEmailCode(@RequestParam @Email String email,@RequestParam String code,@RequestParam String password) throws BackendClientException {
        String redisCode = (String) redisTemplate.opsForValue().get(email);
            if (redisCode == null) {
                throw new BackendClientException("email verify code is expire");
            }
            if (!redisCode.equals(code)) {
                throw new BackendClientException("email verify code is expire");
            }
            userService.changeUserPasswordByEmail(email,password);
    }

//    @PostMapping("/uploadAvatar")
//    public String uploadAvatar(@RequestParam("file") MultipartFile file) throws Exception {
//        String contentType = file.getContentType();
//        if(!contentType.equals("image/png")&&!contentType.equals("image/jpg")){
//            throw new BackendClientException("file only support png or jpg");
//        }
//        UUID uuid = UUID.randomUUID();
//        String type = file.getContentType();
//        type = type.split("/")[1];
//        String fileName = String.format("%s.%s", uuid, type);
//        String url = String.format("avatarimg/%s", fileName);
//        storeAvatar(file.getBytes(),url);
//        return fileName;
//    }
//    private void storeAvatar(byte[] imgData, String fileName) throws Exception {
//        FileOutputStream out = new FileOutputStream(fileName);
//        out.write(imgData);
//        out.close();
//    }
}
