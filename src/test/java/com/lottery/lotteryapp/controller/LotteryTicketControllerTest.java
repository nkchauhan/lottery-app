package com.lottery.lotteryapp.controller;

import com.lottery.lotteryapp.entity.LotteryTicket;
import com.lottery.lotteryapp.service.LotteryTicketService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class LotteryTicketControllerTest {
    @Mock
    private LotteryTicketService lotteryTicketService= new LotteryTicketService();

    @InjectMocks
    private LotteryTicketController lotteryTicketController= new LotteryTicketController();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void purchaseLotteryTest(){
        LotteryTicket lotteryTicket= new LotteryTicket();
        lotteryTicket.setUsername("nchauha9");
        lotteryTicket.setLotteryId(1L);
        lotteryTicket.setLotteryNumber(2L);
        lotteryTicket.setDate(new Date());
        lotteryTicket.setId(1L);

        Mockito.when(lotteryTicketService.purchaseLottery(1L,"nchauha9")).thenReturn(lotteryTicket);
        LotteryTicket lotteryTicket1=lotteryTicketController.purchaseLottery(1L,"nchauha9");
        assertEquals("nchauha9",lotteryTicket1.getUsername());
    }
}
