package io.xt.service;

import io.xt.dto.UserAddDTO;
import io.xt.pojo.User;

public interface UserService {
    User getById(Long userId);
    User getByUsername(String username);
    void add(User user);

}
