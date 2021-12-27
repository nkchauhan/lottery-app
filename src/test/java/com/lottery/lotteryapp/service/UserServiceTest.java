package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.bean.UserBean;
import com.lottery.lotteryapp.entity.Users;
import com.lottery.lotteryapp.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;

public class UserServiceTest {

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UserService userService= new UserService();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUserTest(){
        UserBean userBean= new UserBean();
        userBean.setUserName("nchauha9");
        userBean.setLastName("Chauhan");
        userBean.setFirstName("Nripendra");
        userBean.setEmail("nk_chauhan@live.in");

        Users users= new Users();
        users.setUsername("nchauha9");
        users.setLastName("Chauhan");
        users.setFirstName("Nripendra");
        users.setEmail("nk_chauhan@live.in");

        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(users);
        Users user=userService.createUser(userBean);
        assertEquals("nchauha9",user.getUsername());
    }
}
