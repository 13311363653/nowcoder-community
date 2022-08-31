package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
//@ComponentScan
@RequestMapping("/alpha") //规定初步的请求映射，相对于web应用的根目录
public class AlphaController {

    /*
  2、在controller中调用service的方法
   */
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot."; //访问地址 http://localhost:8080/community/alpha/hello
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find(); //对象.方法名 返回MyBatis
    }


    /*
    Spring MVC演示
     */

    /**
     * @Description 获取请求对象和响应对象
     * @author JBJ
     * @date 2022-07-23 10:08
     */

    @RequestMapping("/http")    //指定地址
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        //请求方式
        System.out.println(request.getMethod());//GET
        //请求路径
        System.out.println(request.getServletPath());// /alpha/http
        //所有请求行的Key
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement(); //获取当前的key
            String value = request.getHeader(name);  //获取key对应的value
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));// http://localhost:8080/community/alpha/http?code=123,会返回123

        //获取响应数据
        //设置返回数据的类型
        response.setContentType("text/html;charset=utf-8");
        //获取输出流

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write("<h1>测试</h1>"); //通过流在浏览器中写出数据
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }


    }

    /**
     * @Description 获取Get请求和路径中的参数。参数采用?拼接
     * @author JBJ
     * @date 2022-07-23 11:00
     * /students/current=10&limit=20
     */
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    //获得request中的参数，需要在方法中声明和参数相同的形参名
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current, //指定参数名称，不需要必须有，如果没有返回默认值
            @RequestParam(name = "limit", required = false, defaultValue = "2") int limit
    ) {
        //当请求中没有相应的参数时 http://localhost:8080/community/alpha/students
        System.out.println(current); //1
        System.out.println(limit); //2

        //http://localhost:8080/community/alpha/students/current=10&limit=20
        return "Some Students"; //由于加了@ResponseBody，将直接在浏览器中输出，不会进行页面跳转
    }

    /**
     * @Description 当参数不使用?，直接变成路径的一部分
     * @author JBJ
     * @date 2022-07-23 11:49
     */
    //  http://localhost:8080/community/alpha/student/101
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    //获取id参数
    public String getStudent(
            @PathVariable("id") Integer id  //注解中指定需要获取参数的名称
    ) {
        System.out.println("id:" + id);
        return "a student";

//        return "student";
    }

    /*
    Post请求 浏览器向服务器提交数据
    Get请求参数会显示在url中，且url的长度有限，所以提交数据时，不采用
     */

    /**
     * @Description Post请求提交数据
     * @author JBJ
     * @date 2022-07-23 12:15
     * //http://localhost:8080/community/alpha/student
     */
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    //此时不能直接通过http://localhost:8080/community/alpha/student访问，会出现Whitelabel Error Page，因该是name和age没有参数
    public String saveStudent(String name, int age) { //形参和表单中参数一致即可
        System.out.println(name);
        System.out.println(age);
        return "success";
//        return "student";
    }

//    @RequestMapping(path = "/student",method = RequestMethod.GET)
//    @ResponseBody
//    public String saveStudent(){ //形参和表单中参数一致即可
////        System.out.println(name);
////        System.out.println(age);
//        return "success";
////        return "student";
//    }

    /**
     * @Description 向浏览器响应HTML数据
     * @author JBJ
     * @date 2022-07-23 13:58
     */
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //不添加注解@ResponseBody，默认返回一个HTML
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        //向请求域共享数据
        //属性名和html中的名称一致才能正确赋值
        mav.addObject("name", "冀柏江");
        mav.addObject("age", 24);
        //设置模板的对象和名字，默认在templates下，不用额外再写
        mav.setViewName("/demo/view");//设置视图，实现页面跳转
        return mav;//将model和view的数据都封装在一起返回给DispatcherServlet
    }

    //View数据直接返回给DispatcherServlet，而DispatcherServlet拥有model的引用，也可以获得结果。
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {//将数据装到model参数中
        //model是DispatcherServlet自动创建的
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 100);
        return "demo/view";//页面跳转
    }

    //响应json数据（异步请求）
    //Java对象-->Json字符串-->Js对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    //DispatcherServlet调用该方法后，看到有@ResponseBody注解，会自动将map对象转换为一个Json字符串
    public Map<String, Object> getEmp() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jbj");
        map.put("age", 24);
        map.put("job", "学生");
        return map;
    }

    @RequestMapping("/emps")
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "emp1");
        emp.put("age", 24);
        emp.put("job", "学生");
        list.add(emp);


        emp = new HashMap<>();
        emp.put("name", "emp2");
        emp.put("age", 25);
        emp.put("job", "学生");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "emp3");
        emp.put("age", 26);
        emp.put("job", "学生");
        list.add(emp);
        return list;
    }

}


