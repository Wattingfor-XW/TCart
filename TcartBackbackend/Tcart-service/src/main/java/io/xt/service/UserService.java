package io.xt.service;

import io.xt.dto.UserAddDTO;
import io.xt.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getById(Long userId);
    User getByUsername(String username);
    void add(UserAddDTO userAddDTO);

}
