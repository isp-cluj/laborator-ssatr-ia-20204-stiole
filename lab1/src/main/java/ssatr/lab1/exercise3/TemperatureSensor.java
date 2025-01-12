package ssatr.lab1.exercise3;

import java.util.Objects;

public class TemperatureSensor extends Sensor {

    private double temperature;

    public TemperatureSensor(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureSensor that = (TemperatureSensor) o;
        return Double.compare(temperature, that.temperature) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(temperature);
    }

    @Override
    public String toString() {
        return "TemperatureSensor{" +
                "temperature=" + temperature +
                '}';
    }

}
