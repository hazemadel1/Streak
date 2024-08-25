package com.example.Streak.Service;

import com.example.Streak.Data.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StreakService {

    private final SegmentService segmentService;
    private final DailyUsageService dailyUsageService;
    private final StreakRepository streakRepository;
    private final RewardRepository rewardRepository;
    private final HistoryService historyService;
    private  final SegmentRepository segmentRepository;

    public StreakService(SegmentService segmentService, DailyUsageService dailyUsageService, StreakRepository streakRepository, RewardRepository rewardRepository, HistoryService historyService, SegmentRepository segmentRepository) {
        this.segmentService = segmentService;
        this.dailyUsageService = dailyUsageService;
        this.streakRepository = streakRepository;
        this.rewardRepository = rewardRepository;
        this.historyService = historyService;
        this.segmentRepository = segmentRepository;
    }

    public String updateDailyUsage(String msisdn, Long dataUsed, Long segmentId) {
        LocalDate today = LocalDate.now();
        Optional<Segment> segment = segmentRepository.findById(segmentId);
        Long minDataUsage = segment.get().limitation();

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
        Optional<Reward> reward = rewardRepository.finddbyCurrentStreak(streak.currentStreak());
        if (reward.isPresent()) {
            History history = new History(null, streak.msisdn(), streak.currentStreak(), reward.get().data());
            historyService.saveStreakHistory(history);
        }
    }
}