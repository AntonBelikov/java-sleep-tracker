package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestNumberOfSessions {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static NumberOfSessions numberOfSessions;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        numberOfSessions = new NumberOfSessions();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(numberOfSessions.apply(testSession));
    }

    @Test
    public void testAmount() {
        SleepAnalysisResult sleepAnalysisResult = new SleepAnalysisResult("Колличество сессий сна: ", 13);
        Assertions.assertEquals(numberOfSessions.apply(testSession), sleepAnalysisResult);
    }
}
