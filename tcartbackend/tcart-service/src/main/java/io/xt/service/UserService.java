package io.xt.service;

import io.xt.dto.UserAddDTO;
import io.xt.dto.UserUpdateDTO;
import io.xt.pojo.User;

public interface UserService {
    public User getUserById(Long userId);
    public void  addUser(UserAddDTO userAddDTO);
    public User getByUsername(String username);
    public void update(UserUpdateDTO userUpdateDTO);
}
