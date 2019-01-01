package io.xt.service;

import io.xt.dto.AddUserDTO;
import io.xt.pojo.User;

public interface UserService {
    public User getUserById(Long userId);
    public void  addUser(AddUserDTO addUserDTO);
    public User getByUsername(String username);
}
