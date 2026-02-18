package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class UserType {
    private String discription;

    public UserType(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return discription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return Objects.equals(discription, userType.discription);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(discription);
    }
}
