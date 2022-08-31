package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //根据id查询User
    User selectById(int id);

    //根据username查询User
    User selectByName(String username);

    //根据email查询User
    User selectByEmail(String email);

    //增加用户User，返回增加的数目
    int insertUser(User user);

    //更新User状态，返回修改条数
    int updateStatus(int id, int status);

    //更新头像，返回修改条数
    int updateHeader(int id, String headerUrl);

    //更新密码，返回修改条数
    int updatePassword(int id, String password);


}
