package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestMinSession {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static MinSession minSession;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        minSession = new MinSession();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(minSession.apply(testSession));
    }

    @Test
    public void testAmount() {
        SleepAnalysisResult sleepAnalysisResult =
                new SleepAnalysisResult("Минимальная длительность сна в минутах: ", 45L);
        Assertions.assertEquals(minSession.apply(testSession), sleepAnalysisResult);
    }
}
