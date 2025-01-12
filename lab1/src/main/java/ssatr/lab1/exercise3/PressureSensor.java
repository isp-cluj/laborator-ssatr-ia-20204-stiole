package ssatr.lab1.exercise3;

import java.util.Objects;

public class PressureSensor extends Sensor {

    private double pressure;

    public PressureSensor(double pressure) {
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PressureSensor that = (PressureSensor) o;
        return Double.compare(pressure, that.pressure) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pressure);
    }

    @Override
    public String toString() {
        return "PressureSensor{" +
                "pressure=" + pressure +
                '}';
    }
}
