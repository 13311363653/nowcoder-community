package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //使得容器可以自动扫描、实现、装配
public interface DiscussPostMapper {
    //分页查询帖子，如果指定id可以查询对应id发布的帖子。offset：起始行的行号，limit：每页显示数据量
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //查询帖子数量
    // @Param注解用于给参数取别名
    int selectDiscussRows(@Param("userId") int userId);//在sql需要动态拼接条件(在<if>中使用)，且该方法只有一个参数，这个参数之前就必须起别名
}
