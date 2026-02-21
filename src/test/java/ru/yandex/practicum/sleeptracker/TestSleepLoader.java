package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestSleepLoader {
    private static List<SleepingSession> testSession;
    private static SleepLoader sleepLoader;

    @BeforeAll
    public static void beforeAll() {
        sleepLoader = new SleepLoader();
        sleepLoader.addWords("src/main/resources/sleep_log.txt");
        testSession = sleepLoader.getListOfSleepingSessions();
    }

    @Test
    public void testMustBeNotEmpty() {
        Assertions.assertFalse(testSession.size() == 0);
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(testSession.size(), 13);
    }

    @Test
    public void testMustBeNotNull() {
        Assertions.assertNotNull(testSession.get(4));
    }

    @Test
    public void testMustBeSame() {
        LocalDateTime start = LocalDateTime.parse("05.10.25 00:10", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime finish = LocalDateTime.parse("05.10.25 06:20", DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepingSession sleepingSession = new SleepingSession(start, finish, SleepQuality.GOOD);
        Assertions.assertEquals(testSession.get(4), sleepingSession);
    }
}
