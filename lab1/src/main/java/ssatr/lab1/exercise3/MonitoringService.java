package ssatr.lab1.exercise3;

import java.util.List;

public class MonitoringService {

    public double getAverageTemperatureSensors(List<TemperatureSensor> temperatureSensors) {
        double temperatureTotal = 0.0;
        double temperatureSensorsCount = 0;

        for (TemperatureSensor temperatureSensor : temperatureSensors) {
            temperatureTotal += temperatureSensor.getTemperature();
            temperatureSensorsCount++;
        }

        return temperatureTotal / temperatureSensorsCount;
    }

    public double getAveragePressureSensors(List<PressureSensor> pressureSensors) {
        double pressureTotal = 0.0;
        double pressureSensorsCount = 0;

        for (PressureSensor pressureSensor : pressureSensors) {
            pressureTotal += pressureSensor.getPressure();
            pressureSensorsCount++;
        }

        return pressureTotal / pressureSensorsCount;
    }

    public double getAverageAllSensors(List<TemperatureSensor> temperatureSensors, List<PressureSensor> pressureSensors) {

        double avgTemp = getAverageTemperatureSensors(temperatureSensors);
        double pressTemp = getAveragePressureSensors(pressureSensors);
        return avgTemp + pressTemp / 2;
    }

}
