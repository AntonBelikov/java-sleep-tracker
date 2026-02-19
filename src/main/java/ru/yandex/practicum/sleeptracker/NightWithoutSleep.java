package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NightWithoutSleep implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {

        if (sleepingSessionsList.isEmpty()) {
            return new SleepAnalysisResult("Введен пустой список.", "Проверьте список");
        }

        LocalDateTime startOfPeriod = sleepingSessionsList.get(0).getStartTime();
        LocalDate endOfPeriod = sleepingSessionsList.get(sleepingSessionsList.size() - 1).getEndTime().toLocalDate();
        int allNights = Period.between(startOfPeriod.toLocalDate(), endOfPeriod).getDays();

        if (startOfPeriod.getHour() < 12) {
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