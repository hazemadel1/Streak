package com.example.Streak.Service;

import com.example.Streak.Data.DailyUsage.DailyUsage;
import com.example.Streak.Data.DailyUsage.DailyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyUsageService {
    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    public void recordDailyUsage(String msisdn, LocalDate today, Long dataUsed) {
       // DailyUsage dailyUsage = new DailyUsage();
//        dailyUsage.Msisdn(msisdn);
//        dailyUsage.setDataUsed(dataUsed);
//        dailyUsage.setDate(today);
        dailyUsageRepository.save(new DailyUsage(null,msisdn,today,dataUsed));
    }
}
