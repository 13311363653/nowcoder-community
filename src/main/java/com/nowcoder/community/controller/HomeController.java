package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页
 * 1：10
 *
 * @author JBJ
 * @date 2022-07-25 10:57
 */
@Controller //controller不需要指定访问路径
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    //定义访问路径和方式
    //首页，userId=0
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model,Page page) {
        //方法调用之前，SpringMVC会自动实例化model和page，并将page注入到Model中
        //所以在thymeleaf中可以直接访问page中对象的数据
        page.setRows(discussPostService.findDiscussPostRows(0));//查找总行数
        page.setPath("index");//不加这个mvc也会给view赋值为index，
        //存储discussPost中的信息
        List<DiscussPost> discussPostsList = discussPostService.findSelect(0, page.getOffset(), page.getLimit());//默认offset=0,limit=10，所以打开index显示最前的10条信息
        //定义一个列表，存储完整的信息（用户信息+discussPosts中的内容）
        List<Map<String, Object>> fullInformationList = new ArrayList<>();

        //实现将帖子信息和用户信息组装
        if (discussPostsList != null) {
            //遍历帖子信息
            for (DiscussPost post : discussPostsList) {
                //初始化map
                Map<String, Object> map = new HashMap<>();
                //将帖子信息装到map中
                map.put("post", post); //（map中的变量名，数据）
                //通过帖子的userId获取对应的user对象
                User user = userService.findUserById(post.getUserID());
                //将user封装到map中
                map.put("user", user);
                //将封装好的信息加到List中
                fullInformationList.add(map);
            }
        }
        //将所有的数据封装到model中
        model.addAttribute("discussPosts", fullInformationList);
        return "index"; //返回逻辑路径为index的页面，由thymeleaf自动改为绝对路径
    }
}
