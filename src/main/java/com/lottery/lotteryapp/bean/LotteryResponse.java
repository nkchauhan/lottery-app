package com.lottery.lotteryapp.bean;

import lombok.Data;

import java.util.Date;

@Data
public class LotteryResponse {
    private Long lotteryId;
    private String lotteryName;
    private Date startDate;
    private String message;
}
