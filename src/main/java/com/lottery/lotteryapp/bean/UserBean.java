package com.lottery.lotteryapp.bean;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserBean {
    @NotNull(message = "User Name is required")
    private String userName;

    @NotNull(message = "First Name is required")
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull(message = "Email is required")
    private String email;
}
