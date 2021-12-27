package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.bean.LotteryResponse;
import com.lottery.lotteryapp.bean.LotteryResultResponse;
import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.repository.LotteryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class LotteryServiceTest {

    @InjectMocks
    private LotteryService lotteryService= new LotteryService();

    @Mock
    private LotteryRepository lotteryRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void initiateLotteryTest(){
        LotteryResponse lotteryResponse= new LotteryResponse();
        lotteryResponse.setLotteryId(1L);
        lotteryResponse.setLotteryName("lotteryA");
        lotteryResponse.setStartDate(new Date());
        lotteryResponse.setMessage("Lottery is Active");

        Lottery lottery = new Lottery();
        lottery.setLotteryName("lotteryA");
        lottery.setStartDate(new Date());

        Mockito.when(lotteryRepository.save(Mockito.any(Lottery.class))).thenReturn(lottery);
        LotteryResponse lotteryResponse1=lotteryService.initiateLottery("lotteryA");
        assertEquals("Lottery is Active",lotteryResponse1.getMessage());
    }

    @Test
    public void lotteryResultsTest(){
        LotteryResultResponse lotteryResultResponse= new LotteryResultResponse("27/12/2021","1");

        Lottery lottery = new Lottery();
        lottery.setLotteryName("lotteryA");
        lottery.setStartDate(new Date());
        lottery.setEndDate(new Date());
        lottery.setLotteryId(1L);
        lottery.setWinnerLotteryNumber(1l);

        Mockito.when(lotteryRepository.findById(1L)).thenReturn(Optional.of(lottery));
        LotteryResultResponse lotteryResultResponse1=lotteryService.lotteryResults(1L);
        assertEquals("1",lotteryResultResponse1.getWinnerLotteryNumber());
    }

}
