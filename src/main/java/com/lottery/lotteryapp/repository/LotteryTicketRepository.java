package com.lottery.lotteryapp.repository;

import com.lottery.lotteryapp.entity.LotteryTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryTicketRepository extends JpaRepository<LotteryTicket, Long> {
    LotteryTicket findFirstByLotteryIdOrderByLotteryNumberDesc(Long lotteryId);

    Long countLotteryTicketByLotteryId(Long lotteryId);

    LotteryTicket findByLotteryNumberAndLotteryId(Long lotteryNumber, Long lotteryId);

}
