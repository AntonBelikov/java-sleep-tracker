package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCountOfBADSessions {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static CountOfBADSessions countOfBADSessions;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        countOfBADSessions = new CountOfBADSessions();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(countOfBADSessions.apply(testSession));
    }

    @Test
    public void testAmount() {
        SleepAnalysisResult sleepAnalysisResult =
                new SleepAnalysisResult("Количество плохих сессий: ", 2);
        Assertions.assertEquals(countOfBADSessions.apply(testSession), sleepAnalysisResult);
    }
}
