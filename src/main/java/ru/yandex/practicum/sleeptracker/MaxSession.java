package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {

        Duration maxDuration = sleepingSessionsList.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStartTime(),
                        sleepingSession.getEndTime()))
                .max(Duration::compareTo)
                .orElse(Duration.ZERO);

        return new SleepAnalysisResult("Максимальная длительность сна в минутах: ", maxDuration.toMinutes());
    }
}
