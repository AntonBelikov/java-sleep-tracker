package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {

        Double averegeTime = sleepingSessionsList.stream()
                .mapToDouble(sleepingSession -> Duration.between(sleepingSession.getStartTime(),
                        sleepingSession.getEndTime()).toMinutes())
                .average()
                .orElseGet(() -> 0.0);

        return new SleepAnalysisResult("Средняя длительность сна в минутах: ", averegeTime);
    }
}
