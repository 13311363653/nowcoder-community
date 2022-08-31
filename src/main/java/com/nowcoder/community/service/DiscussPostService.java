package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对应于DiscussPostMapper的服务类
 * @author JBJ
 * @date 2022-07-25 10:17
 */
@Service
public class DiscussPostService {
    //注入对应的mapper
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * @Description 调用selectDiscussPosts方法，查询数据
     * @author JBJ
     * @date 2022-07-25 10:19 
     */
    public List<DiscussPost> findSelect(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    /**
     * @Description 调用selectDiscussRows方法，查询行数
     * @author JBJ
     * @date 2022-07-25 10:29
     */
    public int  findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussRows(userId);
    }
}
