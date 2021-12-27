package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.bean.UserBean;
import com.lottery.lotteryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserBean userBean) {
        userService.validate(userBean);
        userService.createUser(userBean);
        return ("User Registered successfully");
    }
}
