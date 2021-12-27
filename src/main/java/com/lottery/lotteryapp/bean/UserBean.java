package com.lottery.lotteryapp.bean;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class UserBean {
    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;
}
