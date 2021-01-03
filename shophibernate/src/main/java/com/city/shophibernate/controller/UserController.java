package com.city.shophibernate.controller;

import com.city.shophibernate.model.UserModel;
import com.city.shophibernate.result.ResultOne;
import com.city.shophibernate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "login")
    public ResultOne login(@RequestBody UserModel userModel, HttpSession session) throws Exception {
        ResultOne<Map> resultOne = new ResultOne<Map>();
        HashMap<String, Object> responseData = new HashMap<>();
        if (iUserService.login(userModel) != null) {
            session.setAttribute("user",userModel.getUsername());
            resultOne.setCode(20000);
            resultOne.setMessage("登录成功");
            responseData.put("token", "admin-token");
            resultOne.setData(responseData);
            return resultOne;
        } else {
            resultOne.setCode(60204);
            resultOne.setMessage("账号密码不正确");
            return resultOne;
        }
    }

    @GetMapping(value = "info")
    public ResultOne info() {
        ResultOne<Map> resultOne = new ResultOne<Map>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("roles", "admin");
        responseData.put("name", "超级管理员");
        responseData.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        resultOne.setCode(20000);
        resultOne.setMessage("登录成功");
        resultOne.setData(responseData);
        return resultOne;
    }

    @PostMapping(value = "logout")
    public ResultOne logout(HttpServletRequest request) {
        ResultOne<String> resultOne = new ResultOne<String>();
        request.getSession().removeAttribute("user");
        resultOne.setCode(20000);
        resultOne.setMessage("注销成功");
        resultOne.setData("success");
        return resultOne;
    }
}
