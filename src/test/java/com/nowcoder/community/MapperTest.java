package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author JBJ
 * @date 2022-07-24 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //测试类采用和正式代码相同的配置类
public class MapperTest {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelect() {
        System.out.println(mapper.selectById(101));
        System.out.println(mapper.selectByName("liubei"));
        System.out.println(mapper.selectByEmail("nowcoder101@sina.com"));
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int row = mapper.insertUser(user);
        System.out.println(row);
        System.out.println(user.getId()); //id是数据库自动创建的
        System.out.println(mapper.selectById(150));
    }

    @Test
    public void testUpdate() {
        mapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        mapper.updatePassword(150, "jbjjbj");
        mapper.updateStatus(150, 1);
        System.out.println(mapper.selectById(150));
    }

    @Test
    public void testSelectPosts() {
        //userId为0的情况对应查询主页，不指定某个用户
//        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
//        for (DiscussPost discussPost : discussPosts) {
//            System.out.println(discussPost);
//        }
        //userId为0的情况对应查询主页，不指定某个用户
        int rows = discussPostMapper.selectDiscussRows(0);
        System.out.println(rows);

        List<DiscussPost> discussPosts1 = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost  discussPost: discussPosts1){
            System.out.println(discussPost);
        }

    }

}
