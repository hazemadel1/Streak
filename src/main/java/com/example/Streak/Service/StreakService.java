package com.example.Streak.Service;

import com.example.Streak.Data.History.History;
import com.example.Streak.Data.Reward.Reward;
import com.example.Streak.Data.Reward.RewardRepository;
import com.example.Streak.Data.SegmentConfiguration.SegmentConfiguration;

import com.example.Streak.Data.Streak.Streak;
import com.example.Streak.Data.Streak.StreakRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StreakService {

    private final SegmentConfigurationService segmentConfigurationService;
    private final DailyUsageService dailyUsageService;
    private final StreakRepository streakRepository;
    private final RewardRepository rewardRepository;
    private final HistoryService historyService;
    private final SegmentationService segmentationService;


    public StreakService(SegmentConfigurationService segmentConfigurationService, DailyUsageService dailyUsageService, StreakRepository streakRepository, RewardRepository rewardRepository, HistoryService historyService, SegmentationService segmentationService) {
        this.segmentConfigurationService = segmentConfigurationService;
        this.dailyUsageService = dailyUsageService;
        this.streakRepository = streakRepository;
        this.rewardRepository = rewardRepository;
        this.historyService = historyService;
        this.segmentationService = segmentationService;
    }

    public String updateDailyUsage(String msisdn, Long dataUsed) {
        LocalDate today = LocalDate.now();
        Long segmentId = segmentationService.getSegmentIdByMsisdn(msisdn);
        SegmentConfiguration segmentConfiguration = segmentConfigurationService.getSegmentLimitation(segmentId);
        Long minDataUsage = segmentConfiguration.limitation();

        // Record daily usage
        dailyUsageService.recordDailyUsage(msisdn, today, dataUsed);

        // Get or create streak
        Streak streak = streakRepository.findByMsisdn(msisdn);
        if (streak == null) {
            streak = new Streak(null, msisdn,0, null);
        }

        // Determine if streak should continue or reset
        if (streak.lastStreakDate() != null && !streak.lastStreakDate().equals(today.minusDays(1))) {
            saveStreakHistory(streak);
            streak = new Streak(streak.id(), msisdn, 0, today);
        }

        if (dataUsed >= minDataUsage) {
            streak = new Streak(streak.id(), msisdn, streak.currentStreak() + 1, today);
            streakRepository.save(streak);

            grantRewardIfEligible(streak);
            return "STREAK_ONGOING"; // Indicate that the streak is ongoing
        } else {
            saveStreakHistory(streak);
            streakRepository.save(new Streak(streak.id(), msisdn, 0, today)); // Reset streak if daily goal not met
            return "STREAK_ENDED"; // Indicate that the streak has ended
        }
    }

    private void saveStreakHistory(Streak streak) {
        History history = new History(null, streak.msisdn(), streak.currentStreak(), null);
        historyService.saveStreakHistory(history);
    }

    private void grantRewardIfEligible(Streak streak) {
        Optional<Reward> reward = rewardRepository.findByCurrentStreak(streak.currentStreak());
        if (reward.isPresent()) {
            History history = new History(null, streak.msisdn(), streak.currentStreak(), reward.get().data());
            historyService.saveStreakHistory(history);
        }
    }
}