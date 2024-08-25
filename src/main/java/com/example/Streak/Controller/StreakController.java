package com.example.Streak.Controller;

import com.example.Streak.Service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Streak")
public class StreakController {
    @Autowired
    private StreakService streakService;

    @GetMapping ("/update")
    public ResponseEntity<String> updateDailyUsage(@RequestParam("msisdn") String msisdn, @RequestParam("dataUsed") Long dataUsed, @RequestParam("segmentId") Long segmentId) {
        String result = streakService.updateDailyUsage(msisdn, dataUsed, segmentId);

        switch (result) {
            case "STREAK_ONGOING":
                return ResponseEntity.ok("Streak is ongoing. Daily usage is sufficient.");
            case "STREAK_ENDED":
                return ResponseEntity.ok("Streak ended. Daily usage was insufficient.");
            default:
                return ResponseEntity.status(500).body("Unexpected error occurred.");
        }
    }
}
