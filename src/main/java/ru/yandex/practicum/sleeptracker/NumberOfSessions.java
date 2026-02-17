package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class NumberOfSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        return new SleepAnalysisResult("Колличество сессий сна: ", sleepingSessionsList.size());
    }
}
