package com.example.demo.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RestController
//标记controller，并使得返回值转换为json
@RequestMapping("user")
//表明访问该controller需要http://localhost:8080/user
public class UserController {
    @Autowired(required = false)
    private UserMapper mapper;
    //???

    @PostMapping("login")
    public BaseResult login(@RequestParam(value = "user", defaultValue = "")String user,
                        @RequestParam(value = "pass", defaultValue = "")String pass){
        if(user.equals("")) return new BaseResult(400, "账号必填", "");
        if(pass.equals("")) return new BaseResult(400, "密码必填", "");
        User user1 = mapper.login(user, pass);
        if(user1 == null) {//没有该用户
            return new BaseResult(500, "账号或密码错误", "");
        }else{
            return new BaseResult(200, "", user1);
        }
    }
    @PostMapping
    //新的参数接收方法不需要注解，
    //因为前台传来的form-data形式的参数被后台自动转换成了实体类User对象。
    public BaseResult register(User user){
        //if(user == null || user.getUser() == null || user.getPass() == null)
//        if(user == null || user.getUser() == "" || user.getPass() == "")
//            return new BaseResult(400, "参数不正确", "");
        if (user == null || user.getUser() == null || user.getPass() == null)
            return new BaseResult(400, "参数不正确", "");
        //此处有一个疑虑：可以以空字符串作为user或者pass的值来注册。
        //这涉及到字符串格式，可能需要后续用正则表达式来控制。
        if(mapper.findByUser(user.getUser()) != null)
            return new BaseResult(500, "账号已经注册", "");
        if(mapper.register(user) == 0)
            return new BaseResult(500, "注册失败", "");
        return new BaseResult(200, "", "注册成功！");
    }
}
