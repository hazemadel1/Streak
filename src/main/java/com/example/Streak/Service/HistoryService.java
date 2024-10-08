package com.example.Streak.Service;

import com.example.Streak.Data.History.History;
import com.example.Streak.Data.History.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public void saveStreakHistory(History history) {
        historyRepository.save(history);
    }
}
