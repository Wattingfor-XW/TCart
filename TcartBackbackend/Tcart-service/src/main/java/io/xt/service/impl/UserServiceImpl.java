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
    public void add(User user) {
    User u1 = new User();
        u1.setUsername(user.getUsername());
        u1.setFirstName(user.getFirstName());
        u1.setLastName(user.getLastName());
        u1.setEmail(user.getEmail());
        u1.setAvatarUrl(user.getAvatarUrl());
        u1.setEncryptedPassword(String.valueOf(DigestUtils.md5Digest(user.getPassword().getBytes())));
        u1.setRoles(Constant.rolesStr);
    userMapper.insert(user);
    }
}
