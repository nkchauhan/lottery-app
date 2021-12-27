package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.entity.LotteryTicket;
import com.lottery.lotteryapp.repository.LotteryTicketRepository;
import com.lottery.lotteryapp.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class LotteryTicketService {

    @Autowired
    private LotteryTicketRepository lotteryTicketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private UsersRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTicketService.class);

    public Long selectRandomLotteryWinner(Long lotteryId) {
        Long count = lotteryTicketRepository.countLotteryTicketByLotteryId(lotteryId);

        if (count == 0) {
            LOGGER.info("Couldn't find any participate related to lottery id :: " + lotteryId);
            return -1L;
        }
        long random = getRandom(count);
        LotteryTicket ticket = lotteryTicketRepository.findByLotteryNumberAndLotteryId(random, lotteryId);
        if (ticket == null) {
            throw new IllegalStateException("Lottery ticket couldn't find for this lottery number :: " + random);
        }
        return ticket.getLotteryNumber();
    }
    private long getRandom(Long count) {
        Random rand = new Random();
        return rand.nextInt(count.intValue()) + 1L;
    }

    //Purchase lottery function
    public LotteryTicket purchaseLottery(Long lotteryId, String userName) {
        Lottery lottery = lotteryService.findByLotteryId(lotteryId);

        lotteryStatusCheck(lottery);
        validateUsername(userName);

        LotteryTicket ticket = new LotteryTicket();
        ticket.setLotteryId(lotteryId);
        ticket.setLotteryNumber(generateLotteryNumber(lotteryId));
        ticket.setUsername(userName);
        ticket.setDate(new Date());
        lotteryTicketRepository.save(ticket);

        LOGGER.info("Lottery ticket {} bought successfully for lottery id {} !", ticket.getLotteryNumber(), lotteryId);
        return ticket;
    }
    private Long generateLotteryNumber(Long lotteryId) {
        LotteryTicket ticket = lotteryTicketRepository.findFirstByLotteryIdOrderByLotteryNumberDesc(lotteryId);
        return ticket == null ? 1L : ticket.getLotteryNumber() + 1;
    }

    private void lotteryStatusCheck(Lottery lottery){
        if (lottery.getEndDate() != null) {
            throw new IllegalStateException("Lottery is not active for the id :: " + lottery.getLotteryId());
        }
    }

    private void validateUsername(String userName) {
        if (userName == null || userName.equals("") || userRepository.findByUsername(userName) == null) {
            throw new IllegalStateException("userName not found in db :: " + userName);
        }
    }
}
