package com.example.Streak.Service;

import com.example.Streak.Data.Reward;
import com.example.Streak.Data.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    public Reward getRewardForStreak(int currentStreak) {
        Optional<Reward> rewardOptional = rewardRepository.finddbyCurrentStreak(currentStreak);
        return rewardOptional.orElse(null); // Return null or handle it appropriately
    }
}
