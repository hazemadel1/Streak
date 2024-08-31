# Data Dash: Race to the Prize!

## Overview
**Data Dash: Race to the Prize!** is a streak-based promotional system designed to reward customers based on their daily data usage. The system tracks customers' daily data usage and rewards them when they maintain a streak of usage above a certain threshold for a specified number of consecutive days.

## Features
- **Daily Streak Tracking:** Monitors daily data usage for each customer.
- **Reward System:** Grants rewards based on streak length.
- **Segmentation:** Customers are segmented to vary daily data usage thresholds.
- **Error Handling:** Displays error messages if a customer is not part of the segmentation whitelist.

## Database Schema
### Tables:
- **rewards:** `id`, `data`, `streak_days`
- **daily_usage:** `id`, `msisdn`, `date`, `data_used`
- **streaks:** `id`, `msisdn`, `current_streak`, `last_streak_date`
- **history:** `id`, `msisdn`, `streak_length`, `reward_granted`
- **segmentation:** `msisdn`, `segmentID`
- **segment_configuration:** `id`, `limitation`

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/hazemadel1/Streak.git
