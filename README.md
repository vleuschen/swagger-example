### Swagger-UI

---

### Swagger-UI

随着前后端分离技术的普及，前端和后端之间的交互愈加使得API接口进行前后端开发人员之间联系的纽带，Swagger的出现便是使得后端人员能够更好的书写API文档。

### Swagger简单介绍

Swagger是一个完整且规范的框架，目标是使得客户端和文件系统作为服务器以同样的速度来更新。

作用：

> 1.接口文档的在线生成(swagger-ui.html)。2.功能测试/

#### Swagger是一组开源项目，其中主要要项目如下：

> Swagger-tools:提供各种与Swagger进行集成和交互的工具。例如模 式检验、Swagger 1.2文档转换成Swagger 2.0文档等功能。 
>
> Swagger-core: 用于Java/Scala的的Swagger实现。与JAX-RS(Jersey、 Resteasy、CXF...)、Servlets和Play框架进行集成。 
>
> Swagger-js: 用于JavaScript的Swagger实现。 
>
> Swagger-node-express: Swagger模块，用于node.js的Express web 应用框架。 
>
> Swagger-ui：一个无依赖的HTML、JS和CSS集合，可以为Swagger兼 容API动态生成优雅文档。 
>
>  Swagger-codegen：一个模板驱动引擎，通过分析用户Swagger资源 声明以各种语言生成客户端代码。

### Swagger的使用（以Maven工程为例）

#### 1.导入相关依赖

~~~java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- 导入swagger依赖 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.7.0</version>
        </dependency>
        <!-- 导入swagger依赖结束 -->
~~~

#### 2.配置Swagger类

~~~java
@Configuration
@EnableSwagger2
@EnableWebMvc  //注解一个都不能少
//必须存在，指定扫描的API Controller包
@ComponentScan(basePackages = {"com.cjl.swaggerexample.controller"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        
        //创建文档联系人相关信息
        Contact contact=new Contact("张三","http://www.baidu.com","1071309217@qq.com");

        return new ApiInfoBuilder()
                .title("项目API接口")
                .description("API接口测试")
                .contact(contact)
                .version("1.0.1")
                .build();
    }
}

~~~

#### 3.WebMvc配置

~~~java
@Configuration
public class SwaggerWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


    }
}
~~~

#### 4.在控制类上书写接口信息

~~~java
以下面控制类为例：
@Api(value="用户模块",description = "用户模块的接口信息")
@RestController
public class UserController {

    //模拟数据库
    public static List<User> userList=new ArrayList<>();

    static{
        userList.add(new User(1,"张三","123456"));
        userList.add(new User(2,"李四","123456"));
    }

    //获取所有用户
    @ApiOperation(value = "获取用户列表",notes="获取所有用户")
    @GetMapping("/users")
    public Object users(){
        Map<String,Object> map=new HashMap<>();
        map.put("users",userList);
        return map;
    }

    @ApiOperation(value = "获取单个用户",notes="根据id查询用户")
    @ApiImplicitParam(value = "用户的id",paramType = "path")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id")int id){
        return userList.get(id);
    }

    @ApiOperation(value = "添加用户",notes="根据传入的用户信息添加用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PostMapping("/user")
    public Object add(@RequestBody User user){
        return userList.add(user);
    }


    @ApiOperation(value = "删除用户",notes = "根据传入的用户id删除对应的用户")
    @ApiImplicitParam(value = "用户id",paramType = "path")
    @DeleteMapping("/user/{id}")
    public Object delete(@PathVariable("id")int id){
        return userList.remove(id);
    }

}
~~~

#### 5.在spring boot启动类上加注解

~~~java
@EnableWebMvc
~~~

#### 6.启动项目

访问localhost:8080/swagger-ui.html即可看到可视化文档信息并可以进行相关测试。



感谢b站相关视频的指导，接下来本人将会深入研究Swagger-UI相关内容。以下是视频传送门：

[<https://www.bilibili.com/video/av37961314/?p=1>]




