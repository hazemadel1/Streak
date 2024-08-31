package com.example.Streak.Data.DailyUsage;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface DailyUsageRepository extends CrudRepository<DailyUsage, Long> {
}
