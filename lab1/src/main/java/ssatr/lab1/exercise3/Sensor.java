package ssatr.lab1.exercise3;

import java.util.Objects;

public abstract class Sensor {
    private String installLocation;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(installLocation, sensor.installLocation) && Objects.equals(name, sensor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installLocation, name);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "installLocation='" + installLocation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
