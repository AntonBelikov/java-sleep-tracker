package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NightWithoutSleep implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        LocalDate startOfPeriod = sleepingSessionsList.get(0).getStartTime().toLocalDate();
        LocalDate endOfPeriod = sleepingSessionsList.get(sleepingSessionsList.size() - 1).getEndTime().toLocalDate();
        int allNights = Period.between(startOfPeriod, endOfPeriod).getDays();

        if (sleepingSessionsList.get(0).getStartTime().getHour() < 12) {
            allNights++;
        }

        List<SleepingSession> normalSleepNight = sleepingSessionsList.stream()
                .filter(sleepingSession -> !(sleepingSession.getStartTime().toLocalDate()
                        .equals(sleepingSession.getEndTime().toLocalDate()))
                        || sleepingSession.getStartTime().getHour() < 6)
                .collect(Collectors.toList());

        return new SleepAnalysisResult("Количество бессонных сессий: ", allNights - normalSleepNight.size());
    }
}