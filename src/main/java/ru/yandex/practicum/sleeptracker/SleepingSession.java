package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.util.Objects;

public class SleepingSession {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SleepQuality quality;

    public SleepingSession(LocalDateTime startTime, LocalDateTime endTime, SleepQuality quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, quality);
    }
}

