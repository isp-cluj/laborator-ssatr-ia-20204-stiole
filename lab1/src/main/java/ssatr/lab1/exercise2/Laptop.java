package ssatr.lab1.exercise2;

import java.util.Objects;

public class Laptop implements Chargeable{

    private int level;

    @Override
    public void charge(int duration) {
        System.out.println("Laptop is charging..." + duration);
        level++;
    }

    @Override
    public void getBatteryLevel() {
        System.out.println("Laptop battery level is " + level);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return level == laptop.level;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(level);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "level=" + level +
                '}';
    }
}
