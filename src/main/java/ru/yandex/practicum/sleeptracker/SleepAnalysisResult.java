package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepAnalysisResult {
    private String description;
    private Object result;

    public SleepAnalysisResult(String description, Object result) {
        this.description = description;
        this.result = result;
    }

    @Override
    public String toString() {
        return description + result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepAnalysisResult that = (SleepAnalysisResult) o;
        return Objects.equals(description, that.description) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, result);
    }

    public Object getResult() {
        return result;
    }
}
