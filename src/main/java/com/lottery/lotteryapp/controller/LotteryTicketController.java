package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.entity.LotteryTicket;
import com.lottery.lotteryapp.service.LotteryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lotteryTicket")
public class LotteryTicketController {
    @Autowired
    private LotteryTicketService lotteryTicketService;

    @PostMapping(value = "purchaseLottery/{lotteryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LotteryTicket purchaseLottery(@Valid @PathVariable("lotteryId") Long lotteryId, @Valid @RequestParam("userName") String userName) {
        return lotteryTicketService.purchaseLottery(lotteryId, userName);
    }
}
