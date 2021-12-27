package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.bean.UserBean;
import com.lottery.lotteryapp.entity.Users;
import com.lottery.lotteryapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsersRepository userRepository;

    public void validate(UserBean userBean) {
        if (userRepository.findByUsername(userBean.getUserName()) != null) {
            throw new IllegalStateException("user already exist :: " + userBean.getUserName());
        }
    }

    public Users createUser(UserBean userBean) {
        Users user = new Users();
        user.setFirstName(userBean.getFirstName());
        user.setLastName(userBean.getLastName());
        user.setUsername(userBean.getUserName());
        user.setEmail(userBean.getEmail());
        return userRepository.save(user);
    }
}
