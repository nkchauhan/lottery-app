package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.bean.UserBean;
import com.lottery.lotteryapp.service.UserService;
import com.lottery.lotteryapp.util.ApiValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserBean userBean) {
        Set<ConstraintViolation<UserBean>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(userBean);
        if (!constraintViolations.isEmpty()) {
            ApiValidation.getConstraintViolationsAndThrowException(constraintViolations);
        }
        userService.validate(userBean);
        userService.createUser(userBean);
        return ("User Registered successfully");
    }
}
