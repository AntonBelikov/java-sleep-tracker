package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SleepLoader {
    private List<String> sleepSessionsString;

    public SleepLoader() {
        sleepSessionsString = new ArrayList<>();
    }

    public void addWords(String file) {
        Path filePath = Paths.get(file);
        try {
            sleepSessionsString = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SleepingSession> getListOfSleepingSessions() {
        return sleepSessionsString.stream()
                .map(line -> line.split(";"))
                .filter(parts -> parts.length == 3)
                .map(parts -> new SleepingSession(
                        LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")),
                        LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")),
                        SleepQuality.valueOf(parts[2])))
                .collect(Collectors.toList());
    }
}

