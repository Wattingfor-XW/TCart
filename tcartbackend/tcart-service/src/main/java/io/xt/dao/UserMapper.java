package io.xt.dao;

import com.github.pagehelper.Page;
import io.xt.dto.UserListDTO;
import io.xt.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page<User> selectWithPage();

    User selectByEmail(String email);

}