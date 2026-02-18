package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestAverageDuration {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;
    private static AverageDuration averageDuration;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
        averageDuration = new AverageDuration();
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(averageDuration.apply(testSession));
    }

    @Test
    public void testAmount() {
        Assertions.assertEquals(averageDuration.apply(testSession).getResult(), 345.38461538461536);
    }
}
