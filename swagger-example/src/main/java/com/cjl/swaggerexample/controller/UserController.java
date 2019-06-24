package com.cjl.swaggerexample.controller;

import com.cjl.swaggerexample.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
