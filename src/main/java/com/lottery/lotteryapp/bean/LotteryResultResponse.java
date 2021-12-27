package com.lottery.lotteryapp.bean;

import lombok.Data;

@Data
public class LotteryResultResponse {
    private String date;
    private String winnerLotteryNumber;

    public LotteryResultResponse(String lotteryDate, String winnerNumber) {
        this.date=lotteryDate;
        this.winnerLotteryNumber=winnerNumber;
    }
}
