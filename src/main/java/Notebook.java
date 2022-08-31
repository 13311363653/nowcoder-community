/**
 * @author JBJ
 * @date 2022-07-21 15:27
 *
 * IoC思想:
 *  控制反转，是一个面对对象编程的思想
 * Dependence Injection
 *  依赖注入，是IoC思想的实现方式，减小对象间的耦合
 * IoC Container
 * IoC容器，是实现依赖注入的关键，本质上是一个工厂
 *
 * 服务端三层架构 表现层、业务层、数据访问层
 * Spring MVC（都在表现层）:
 *      Model:模型层  View:视图层   Controller:控制层
 *      浏览器发送请求访问服务器，访问控制层，控制层接受请求中的数据调用业务层处理，将处理完的数据封装到
 *      Model中，然后再传给View，View利用Model中的数据生成HTML返回给浏览器。
 *核心组件：前端控制器：DispatcherServlet
 *
 * Thymeleaf 模板引擎：生成动态的HTML,倡导自然模板，以HTML文件为模板
 * 模板文件和Model-->模板引擎-->动态HTML
 * 常用语法：标准表达式、判断与循环、模板的布局
 *
 * MyBatis
 * 核心组件
 *  SqlSessionFactory；用于创建SqlSession的工厂类
 *  SqlSession:用于向数据库执行SQL
 *  主配置文件：XML配置文件，可以对MyBatis的底层行为做出详细的配置
 *  Mapper接口：就是DAO接口，在MyBatis中称为Mapper
 *  Mapper映射器：用于编写SQL,并将SQL和实体类映射的组件采用xml,注解均可实现
 */


public class Notebook {


}
