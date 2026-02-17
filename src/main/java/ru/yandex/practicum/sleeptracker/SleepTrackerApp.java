package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {
        SleepLoader sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        // создаем список, содержащий SleepingSession
        List<SleepingSession> sleepingData = sleepLoader.getListOfSleepingSessions();

        // создаем список, содержащий классы, которые реализуют Function
        List<Function<List<SleepingSession>, SleepAnalysisResult>> functionsList = new ArrayList();

        functionsList.add(new NumberOfSessions());
        functionsList.add(new MaxSession());
        functionsList.add(new MinSession());
        functionsList.add(new AverageDuration());
        functionsList.add(new CountOfBADSessions());

        // выводим результаты анализа
        functionsList.forEach(function -> System.out.println(function.apply(sleepingData)));


    }
}