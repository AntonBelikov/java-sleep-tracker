package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOfBADSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        List<SleepQuality> badCount = sleepingSessionsList.stream()
                .map(sleepingSession -> sleepingSession.getQuality())
                .filter(sleepQuality -> sleepQuality.equals(SleepQuality.BAD))
                .collect(Collectors.toList());
        return new SleepAnalysisResult("Количество плохих сессий: ", badCount.size());
    }
}