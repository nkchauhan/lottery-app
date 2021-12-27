package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.bean.UserBean;
import com.lottery.lotteryapp.entity.Users;
import com.lottery.lotteryapp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;

public class UserControllerTest {
    @Mock
    private UserService userService= new UserService();

    @InjectMocks
    private UserController userController= new UserController();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerTest(){
        UserBean userBean= new UserBean();
        userBean.setUserName("nchauha9");
        userBean.setLastName("Chauhan");
        userBean.setFirstName("Nripendra");
        userBean.setEmail("nk_chauhan@live.in");

        Users users= new Users();
        users.setUsername("nchauha9");

        Mockito.when(userService.createUser(userBean)).thenReturn(users);
        String user=userController.register(userBean);
        assertEquals("User Registered successfully",user);
    }
}