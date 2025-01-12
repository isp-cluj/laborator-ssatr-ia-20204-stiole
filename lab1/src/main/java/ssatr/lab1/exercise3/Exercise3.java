package ssatr.lab1.exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise3 {
    public static void main(String[] args) {
        List<TemperatureSensor> temperatureSensors = new ArrayList<>();
        List<PressureSensor> pressureSensors = new ArrayList<>();

        TemperatureSensor tS1 = new TemperatureSensor(5.3);
        TemperatureSensor tS2 = new TemperatureSensor(2.3);
        TemperatureSensor tS3 = new TemperatureSensor(5.8);

        temperatureSensors.add(tS1);
        temperatureSensors.add(tS2);
        temperatureSensors.add(tS3);

        PressureSensor pS1 = new PressureSensor(1.1);
        PressureSensor pS2 = new PressureSensor(1.8);
        PressureSensor pS3 = new PressureSensor(8.1);
        PressureSensor pS4 = new PressureSensor(2.7);

        pressureSensors.add(pS1);
        pressureSensors.add(pS2);
        pressureSensors.add(pS3);
        pressureSensors.add(pS4);

        MonitoringService monitoringService = new MonitoringService();

        double tempAvg = monitoringService.getAverageTemperatureSensors(temperatureSensors);
        System.out.println(tempAvg);

        System.out.println(monitoringService.getAverageAllSensors(temperatureSensors, pressureSensors));
    }

}
