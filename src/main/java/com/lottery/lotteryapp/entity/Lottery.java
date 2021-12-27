package com.lottery.lotteryapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "lottery")
public class Lottery {

    @Id
    @Column(name = "lottery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotteryId;

    @Column(name = "lottery_name")
    private String lotteryName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "winner_lottery_number")
    private Long winnerLotteryNumber;
}
