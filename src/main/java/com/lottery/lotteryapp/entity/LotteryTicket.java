package com.lottery.lotteryapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "lottery_ticket")
public class LotteryTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lottery_id")
    private Long lotteryId;

    @Column(name = "username")
    private String username;

    @Column(name = "lottery_number")
    private Long lotteryNumber;

    @Column(name = "date")
    private Date date;
}
