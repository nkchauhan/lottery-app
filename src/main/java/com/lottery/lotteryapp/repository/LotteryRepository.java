package com.lottery.lotteryapp.repository;

import com.lottery.lotteryapp.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    List<Lottery> findLotteriesByEndDateIsNull();

    Long countByLotteryNameAndEndDateIsNull(String name);
}
