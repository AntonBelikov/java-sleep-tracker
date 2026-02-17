package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        //Assertions.assertNotNull();
    }

    @Test
    public void testAmount() {
        //SleepAnalysisResult sleepAnalysisResult = new SleepAnalysisResult("Колличество сессий сна: ", 13);
       // Assertions.assertEquals(), sleepAnalysisResult);
    }
}
