package ssatr.lab1.exercise4;

public class Ticket {
    
    private String eventName;
    private int price;

    public Ticket(String eventName, int price) {
        this.eventName = eventName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" + "eventName=" + eventName + ", price=" + price + '}';
    }
    
}
