package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NightWithoutSleep implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        //Period period = Period.between(sleepingSessionsList.get(sleepingSessionsList.size() - 1).getEndTime(), sleepingSessionsList.get(0).getStartTime());
        List<SleepingSession> notSleepNight = sleepingSessionsList.stream()
                .filter(sleepingSession -> !(sleepingSession.getStartTime().toLocalDate().equals(sleepingSession.getEndTime().toLocalDate())))
                .collect(Collectors.toList());
        return new SleepAnalysisResult("Количество плохих сессий: ", notSleepNight.size());
    }
}