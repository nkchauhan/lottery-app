package com.lottery.lotteryapp.service;

import com.lottery.lotteryapp.bean.LotteryResponse;
import com.lottery.lotteryapp.bean.LotteryResultResponse;
import com.lottery.lotteryapp.entity.Lottery;
import com.lottery.lotteryapp.repository.LotteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {

    @Autowired
    private LotteryTicketService lotteryTicketService;

    @Autowired
    private LotteryRepository lotteryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryService.class);

    public void endCurrentLotteryEventAndDrawWinners() {
        List<Lottery> lotteries = getActiveLotteries();
        lotteries.stream().forEach(lottery -> {
            try {
                endLotteryAndDrawLotteryWinner(lottery.getLotteryId());
            } catch (Exception e) {
                LOGGER.error("Error in ending lottery event {} , error: {} ", lottery.getLotteryId(), e.getMessage());
            }
        });
    }

    private void endLotteryAndDrawLotteryWinner(Long lotteryId) {
        checkLotteryIsPassive(lotteryId);
        Long winnerNumber = lotteryTicketService.selectRandomLotteryWinner(lotteryId);
        Lottery lottery = findByLotteryId(lotteryId);
        lottery.setWinnerLotteryNumber(winnerNumber);
        lottery.setEndDate(new Date());
        lotteryRepository.save(lottery);
    }
    private void checkLotteryIsPassive(Long lotteryId) {
        Lottery lottery = findByLotteryId(lotteryId);
        if (lottery.getEndDate() != null) {
            throw new IllegalArgumentException("Lottery id is already passive! :: " + lotteryId);
        }
    }
    public Lottery findByLotteryId(Long lotteryId) {
        Optional<Lottery> lottery = lotteryRepository.findById(lotteryId);
        if (lottery.isEmpty()) {
            throw new IllegalArgumentException("Lottery not found for this id :: " + lotteryId);
        }
        return lottery.get();
    }
    public List<Lottery> getActiveLotteries() {
        return lotteryRepository.findLotteriesByEndDateIsNull();
    }

    public LotteryResponse initiateLottery(String lotteryName) {
        validateLottery(lotteryName);
        LotteryResponse lotteryResponse = new LotteryResponse();
        Lottery lottery = new Lottery();
        lottery.setLotteryName(lotteryName);
        lottery.setStartDate(new Date());
        Lottery lotteryResp=lotteryRepository.save(lottery);
        if(lotteryResp!=null) {
            lotteryResponse.setLotteryId(lotteryResp.getLotteryId());
            lotteryResponse.setLotteryName(lotteryResp.getLotteryName());
            lotteryResponse.setStartDate(lotteryResp.getStartDate());
            lotteryResponse.setMessage("Lottery is Active");
        }
        return lotteryResponse;
    }
    public void validateLottery(String lotteryName){
        Long count = lotteryRepository.countByLotteryNameAndEndDateIsNull(lotteryName);
        if (count > 0) {
            throw new IllegalArgumentException("There is already an active lottery by the name :: " + lotteryName);
        }
    }

    //To get Lottery results
    public LotteryResultResponse lotteryResults(Long lotteryId) {
        checkLotteryIsActive(lotteryId);
        return getlotteryResults(lotteryId);
    }

    private LotteryResultResponse getlotteryResults(Long lotteryId) {
        Lottery lottery = findByLotteryId(lotteryId);
        String winnerNumber = (lottery.getWinnerLotteryNumber() == -1 ? "No winner" : lottery.getWinnerLotteryNumber().toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String lotteryDate = dateFormat.format(lottery.getEndDate());

        return new LotteryResultResponse(lotteryDate, winnerNumber);
    }

    private void checkLotteryIsActive(Long lotteryId){
        Lottery lottery = findByLotteryId(lotteryId);
        if (lottery.getEndDate() == null) {
            throw new IllegalArgumentException("Lottery is not yet finished :: " + lotteryId);
        }
    }
}
