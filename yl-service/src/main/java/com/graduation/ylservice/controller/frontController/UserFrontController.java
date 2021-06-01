package com.graduation.ylservice.controller.frontController;

import com.graduation.ylservice.entity.UserEntity;
import com.graduation.ylservice.service.UserService;
import com.graduation.ylservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/ylservice/userFront")
@RestController
public class UserFrontController {

    @Autowired
    private UserService userService;

    @PostMapping("checkLogin")
    public R checkLogin(@RequestBody UserEntity user, HttpServletRequest request){
        return userService.checkLogin(user,request) ? R.ok() : R.error("用户信息输入错误");
    }

}
