package io.xt.service.impl;

import io.xt.dao.UserMapper;
import io.xt.dto.AddUserDTO;
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
    public void addUser(AddUserDTO addUserDTO) {
        User user = new User();
        user.setUsername(addUserDTO.getUsername());
        user.setEmail(addUserDTO.getEmail());
        user.setFirstName(addUserDTO.getFirstName());
        user.setAvatarUrl(addUserDTO.getAvatarUrl());
        user.setLastName(addUserDTO.getLastName());
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(addUserDTO.getPassword().getBytes()));
        user.setRoles(Constant.rolesStr);
        userMapper.insert(user);
    }
}
