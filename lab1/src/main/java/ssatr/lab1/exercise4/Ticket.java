package ssatr.lab1.exercise4;

import java.util.Objects;

public class Ticket {

    private String eventName;
    private String ticketHolder;
    private double price;

    public Ticket(String eventName, String ticketHolder, double price) {
        this.eventName = eventName;
        this.ticketHolder = ticketHolder;
        this.price = price;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0 && Objects.equals(eventName, ticket.eventName) && Objects.equals(ticketHolder, ticket.ticketHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, ticketHolder, price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventName='" + eventName + '\'' +
                ", ticketHolder='" + ticketHolder + '\'' +
                ", price=" + price +
                '}';
    }
}
