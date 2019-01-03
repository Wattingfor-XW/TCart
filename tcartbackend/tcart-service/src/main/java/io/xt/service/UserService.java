package io.xt.service;

import com.github.pagehelper.PageInfo;
import io.xt.dto.UserAddDTO;
import io.xt.dto.UserListDTO;
import io.xt.dto.UserUpdateDTO;
import io.xt.pojo.User;

public interface UserService {
    public User getUserById(Long userId);
    public void  addUser(UserAddDTO userAddDTO);
    public User getByUsername(String username);
    public void update(UserUpdateDTO userUpdateDTO);
    public PageInfo<UserListDTO> getUsersWithPage(Integer pageNum);
    public void batchDelect(Long userId);
}
