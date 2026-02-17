package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestMaxSession {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static MaxSession maxSession;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        maxSession = new MaxSession();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(maxSession.apply(testSession));
    }

    @Test
    public void testAmount() {
        SleepAnalysisResult sleepAnalysisResult =
                new SleepAnalysisResult("Максимальная длительность сна в минутах: ", 500L);
        Assertions.assertEquals(maxSession.apply(testSession), sleepAnalysisResult);
    }
}
