package io.xt.service.impl;

import io.xt.dao.UserMapper;
import io.xt.dto.UserAddDTO;
import io.xt.pojo.User;
import io.xt.service.UserService;
import io.xt.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public void add(UserAddDTO userAddDTO) {
    User user = new User();
    user.setUsername(userAddDTO.getUsername());
    user.setFirstName(userAddDTO.getFirstName());
    user.setLastName(userAddDTO.getLastName());
    user.setEmail(userAddDTO.getEmail());
    user.setAvatarUrl(userAddDTO.getAvatarUrl());
    user.setEncryptedPassword(DigestUtils.md5Digest(userAddDTO.getPassword().getBytes()));
    user.setRoles(Constant.rolesStr);
    userMapper.insert(user);
    }
}
