package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.controller.LotteryTicketController;
import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.entity.LotteryTicket;
import com.lottery.lotteryapp.entity.Users;
import com.lottery.lotteryapp.repository.LotteryTicketRepository;
import com.lottery.lotteryapp.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class LotteryTicketServiceTest {

    @InjectMocks
    private LotteryTicketService lotteryTicketService= new LotteryTicketService();

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private LotteryTicketRepository lotteryTicketRepository;

    @Mock
    private LotteryService lotteryService= new LotteryService();

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

        Lottery lottery= new Lottery();
        lottery.setLotteryName("lotteryA");

        Users users= new Users();
        users.setUsername("nchauha9");

        Mockito.when(lotteryTicketRepository.save(Mockito.any(LotteryTicket.class))).thenReturn(lotteryTicket);
        Mockito.when(lotteryService.findByLotteryId(1L)).thenReturn(lottery);
        Mockito.when(usersRepository.findByUsername("nchauha9")).thenReturn(users);
        //Mockito.when(lotteryTicketRepository.countLotteryTicketByLotteryId(1L)).thenReturn(1L);
        //Mockito.when(lotteryTicketRepository.findByLotteryNumberAndLotteryId(1L,1L)).thenReturn(new LotteryTicket());
        LotteryTicket lotteryTicket1=lotteryTicketService.purchaseLottery(1L,"nchauha9");
        assertEquals("nchauha9",lotteryTicket1.getUsername());
    }
}
