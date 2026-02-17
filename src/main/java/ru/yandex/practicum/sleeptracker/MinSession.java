package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        Duration minDuration = sleepingSessionsList.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStartTime(),
                        sleepingSession.getEndTime()))
                .min(Duration::compareTo)
                .orElse(Duration.ZERO);
        return new SleepAnalysisResult("Минимальная длительность сна в минутах: ", minDuration.toMinutes());
    }
}
