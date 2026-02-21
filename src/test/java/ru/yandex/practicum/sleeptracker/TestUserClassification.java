package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestUserClassification {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static UserClassification userClassification;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        userClassification = new UserClassification();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(userClassification.apply(testSession));
    }

    @Test
    public void testType() {
        SleepAnalysisResult sleepAnalysisResult = new SleepAnalysisResult("По времени сна вы: ", new UserType("Голубь"));
        Assertions.assertEquals(userClassification.apply(testSession), sleepAnalysisResult);
    }

    @Test
    public void testTypeMustBeOwl() {
        LocalDateTime start1 = LocalDateTime.parse("28.02.25 23:10", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish1 = LocalDateTime.parse("01.03.25 09:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession1 = new SleepingSession(start1, finish1, SleepQuality.GOOD);

        LocalDateTime start2 = LocalDateTime.parse("02.03.25 01:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish2 = LocalDateTime.parse("02.03.25 09:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession2 = new SleepingSession(start2, finish2, SleepQuality.GOOD);

        List<SleepingSession> testMonth = new ArrayList<>();
        testMonth.add(sleepingSession1);
        testMonth.add(sleepingSession2);

        Assertions.assertEquals(userClassification.apply(testMonth).getResult(), new UserType("Сова"));
    }

    @Test
    public void testTypeMustBeLark() {
        LocalDateTime start1 = LocalDateTime.parse("28.02.25 20:10", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish1 = LocalDateTime.parse("01.03.25 06:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession1 = new SleepingSession(start1, finish1, SleepQuality.GOOD);

        LocalDateTime start2 = LocalDateTime.parse("01.03.25 21:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish2 = LocalDateTime.parse("02.03.25 05:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession2 = new SleepingSession(start2, finish2, SleepQuality.GOOD);

        List<SleepingSession> testMonth = new ArrayList<>();
        testMonth.add(sleepingSession1);
        testMonth.add(sleepingSession2);

        Assertions.assertEquals(userClassification.apply(testMonth).getResult(), new UserType("Жаворонок"));
    }

    @Test
    public void testTypeMustBePegeon() {
        LocalDateTime start1 = LocalDateTime.parse("28.02.25 20:10", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish1 = LocalDateTime.parse("01.03.25 06:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession1 = new SleepingSession(start1, finish1, SleepQuality.GOOD);

        LocalDateTime start2 = LocalDateTime.parse("02.03.25 01:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish2 = LocalDateTime.parse("02.03.25 09:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession2 = new SleepingSession(start2, finish2, SleepQuality.GOOD);

        List<SleepingSession> testMonth = new ArrayList<>();
        testMonth.add(sleepingSession1);
        testMonth.add(sleepingSession2);

        Assertions.assertEquals(userClassification.apply(testMonth).getResult(), new UserType("Голубь"));
    }
}
