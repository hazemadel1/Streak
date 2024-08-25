package com.example.Streak.Data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreakRepository extends CrudRepository<Streak, Long> {
    Streak findByMsisdn(String msisdn);
}

