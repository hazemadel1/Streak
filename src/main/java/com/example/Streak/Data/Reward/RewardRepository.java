package com.example.Streak.Data.Reward;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RewardRepository extends CrudRepository<Reward, Long> {

    @Query("SELECT * FROM rewards WHERE streak_days = :streakDays")
    Optional<Reward> findByCurrentStreak(int streakDays);
}
