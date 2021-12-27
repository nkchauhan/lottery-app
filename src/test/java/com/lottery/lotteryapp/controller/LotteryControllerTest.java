package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.bean.LotteryResponse;
import com.lottery.lotteryapp.bean.LotteryResultResponse;
import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.service.LotteryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LotteryControllerTest {

    @Mock
    private LotteryService lotteryService= new LotteryService();

    @InjectMocks
    private LotteryController lotteryController= new LotteryController(lotteryService);

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

        Mockito.when(lotteryService.initiateLottery("lotteryA")).thenReturn(lotteryResponse);
        LotteryResponse lotteryResponse1=lotteryController.initiateLottery("lotteryA");
        assertEquals("Lottery is Active",lotteryResponse1.getMessage());
    }

    @Test
    public void lotteryResultsTest(){
        LotteryResultResponse lotteryResultResponse= new LotteryResultResponse("27/12/2021","1");

        Mockito.when(lotteryService.lotteryResults(1L)).thenReturn(lotteryResultResponse);
        LotteryResultResponse lotteryResultResponse1=lotteryController.lotteryResults(1L);
        assertEquals("1",lotteryResultResponse1.getWinnerLotteryNumber());
    }

    @Test
    public void activeLotteriesTest(){
        List<Lottery> lotteryList= new ArrayList<Lottery>();
        Lottery lottery= new Lottery();
        lottery.setLotteryName("lotteryA");
        lotteryList.add(lottery);

        Mockito.when(lotteryService.getActiveLotteries()).thenReturn(lotteryList);
        List<Lottery> lotteryList1=lotteryController.getActiveLotteries();
        assertEquals("lotteryA",lotteryList1.get(0).getLotteryName());
    }
}
