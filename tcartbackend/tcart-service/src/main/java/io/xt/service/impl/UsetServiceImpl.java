package io.xt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xt.dao.UserMapper;
import io.xt.dto.UserAddDTO;
import io.xt.dto.UserListDTO;
import io.xt.dto.UserUpdateDTO;
import io.xt.pojo.User;
import io.xt.service.UserService;
import io.xt.uitl.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UsetServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public void addUser(UserAddDTO userAddDTO) {
        User user = new User();
        user.setUsername(userAddDTO.getUsername());
        user.setEmail(userAddDTO.getEmail());
        user.setFirstName(userAddDTO.getFirstName());
        user.setAvatarUrl(userAddDTO.getAvatarUrl());
        user.setLastName(userAddDTO.getLastName());
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(userAddDTO.getPassword().getBytes()));
        user.setRoles(Constant.rolesStr);
        userMapper.insert(user);
    }

    @Override
    public User getByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        User user = userMapper.selectByPrimaryKey(userUpdateDTO.getUserId());
        user.setUsername(userUpdateDTO.getUsername());
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setAvatarUrl(userUpdateDTO.getAvatarUrl());
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(userUpdateDTO.getPassword().getBytes()));
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public PageInfo<UserListDTO> getUsersWithPage(Integer pageNum) {
       //todo chang page size
        PageHelper.startPage(pageNum,10);
        Page<UserListDTO> users = userMapper.selectWithPage();
        PageInfo<UserListDTO> userPageInfo = users.toPageInfo();
        return userPageInfo;
    }

    @Override
    public void batchDelect(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void changeUserPasswordByEmail(String email, String password) {
        User user = userMapper.selectByEmail(email);
        user.setEncryptedPassword(password);
        userMapper.updateByPrimaryKey(user);

    }


}
