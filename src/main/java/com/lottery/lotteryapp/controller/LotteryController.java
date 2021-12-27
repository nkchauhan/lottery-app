package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.bean.LotteryResponse;
import com.lottery.lotteryapp.bean.LotteryResultResponse;
import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.service.LotteryService;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    private LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService){
        this.lotteryService=lotteryService;
    }

    // Runs at 12:00 AM every day to end active lottery and select winners
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("/endCurrentLotteryEventAndDrawWinners")
    public void endLotteryAndSelectRandomLotteryWinner() {
        lotteryService.endCurrentLotteryEventAndDrawWinners();
    }

    @PostMapping(value = "/initiateLottery")
    public LotteryResponse initiateLottery(@Valid @RequestParam("lotteryName") String lotteryName){
        return lotteryService.initiateLottery(lotteryName);
    }

    @GetMapping(value = "/lotteryResults/{lotteryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LotteryResultResponse lotteryResults(@Valid @PathVariable("lotteryId") Long lotteryId){
        return lotteryService.lotteryResults(lotteryId);
    }

    @GetMapping(value = "/activeLotteries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lottery> getActiveLotteries() {
        return lotteryService.getActiveLotteries();
    }
}
