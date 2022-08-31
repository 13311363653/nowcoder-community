package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JBJ
 * @date 2022-07-25 10:48
 */
@Service
public class UserService {
    @Autowired
   private UserMapper userMapper;
    /**
     * @Description 通过userID查询到User对象
     * @author JBJ
     * @date 2022-07-25 10:49
     */

    public User findUserById(int userId){
        return userMapper.selectById(userId);
    }
}
