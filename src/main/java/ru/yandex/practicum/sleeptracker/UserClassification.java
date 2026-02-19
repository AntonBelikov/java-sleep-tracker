package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionsList) {
        UserType userType;

        if (sleepingSessionsList.isEmpty()) {
            return new SleepAnalysisResult("Введен пустой список.", "Проверьте список");
        }

        List<SleepingSession> normalSleepNight = sleepingSessionsList.stream()
                .filter(sleepingSession -> !(sleepingSession.getStartTime().toLocalDate()
                        .equals(sleepingSession.getEndTime().toLocalDate()))
                        || sleepingSession.getStartTime().getHour() < 6)
                .collect(Collectors.toList());

        int onlyNormalNightCount = normalSleepNight.size();

        List<SleepingSession> nightOfOwl = normalSleepNight.stream()
                .filter(sleepingSession -> ((sleepingSession.getStartTime().getHour() >= 23
                        || sleepingSession.getStartTime().getHour() < 2)
                        && sleepingSession.getEndTime().getHour() >= 9))
                .collect(Collectors.toList());

        int owlNightCount = nightOfOwl.size();

        List<SleepingSession> nightOfLark = normalSleepNight.stream()
                .filter(sleepingSession -> ((sleepingSession.getStartTime().getHour() < 22
                        && sleepingSession.getStartTime().getHour() >= 19)
                        && sleepingSession.getEndTime().getHour() < 7))
                .collect(Collectors.toList());

        int larkNightCount = nightOfLark.size();
        int pegeonNightCount = onlyNormalNightCount - owlNightCount - larkNightCount;

        if (owlNightCount > larkNightCount && owlNightCount > pegeonNightCount) {
            userType = new UserType("Сова");
        } else if (larkNightCount > pegeonNightCount && larkNightCount != owlNightCount) {
            userType = new UserType("Жаворонок");
        } else {
            userType = new UserType("Голубь");
        }

        return new SleepAnalysisResult("По времени сна вы: ", userType);
    }
}
