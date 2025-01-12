package ssatr.lab1.exercise2;

public class Laptop implements Chargeable{

    private int level;
    @Override
    public void charge(int duration) {
        System.out.println("Laptop is chargind..."+duration);
        level++;
    }

    @Override
    public void getBatteryLevel() {
        System.out.println("Laptop battery level is "+level);
    }
}
