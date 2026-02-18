package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestNightWithoutSleep {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static NightWithoutSleep nightWithOutSleep;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        nightWithOutSleep = new NightWithoutSleep();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(nightWithOutSleep.apply(testSession));
    }

    @Test
    public void testAmount() {
        SleepAnalysisResult sleepAnalysisResult = new SleepAnalysisResult("Количество бессонных сессий: ", 20);
        Assertions.assertEquals(nightWithOutSleep.apply(testSession), sleepAnalysisResult);
    }

    @Test
    public void testCountOfNigthOnBorderOfMonth() {
        LocalDateTime start1 = LocalDateTime.parse("28.02.25 23:10", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish1 = LocalDateTime.parse("01.03.25 06:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession1 = new SleepingSession(start1, finish1, SleepQuality.GOOD);
        List<SleepingSession> testMonth = new ArrayList<>();

        testMonth.add(sleepingSession1);

        Assertions.assertEquals(nightWithOutSleep.apply(testMonth).getResult(), 0);

        LocalDateTime start2 = LocalDateTime.parse("02.03.25 06:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish2 = LocalDateTime.parse("02.03.25 09:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession2 = new SleepingSession(start2, finish2, SleepQuality.GOOD);

        testMonth.add(sleepingSession2);

        Assertions.assertEquals(nightWithOutSleep.apply(testMonth).getResult(), 1);
    }

    @Test
    public void testCountOfNigthWithDayBefore12() {
        LocalDateTime start1 = LocalDateTime.parse("02.03.25 03:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish1 = LocalDateTime.parse("02.03.25 08:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession1 = new SleepingSession(start1, finish1, SleepQuality.GOOD);

        LocalDateTime start2 = LocalDateTime.parse("02.03.25 23:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish2 = LocalDateTime.parse("03.03.25 08:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession2 = new SleepingSession(start2, finish2, SleepQuality.GOOD);

        List<SleepingSession> testMonth = new ArrayList<>();

        testMonth.add(sleepingSession1);
        testMonth.add(sleepingSession2);

        Assertions.assertEquals(nightWithOutSleep.apply(testMonth).getResult(), 0);
    }
}
