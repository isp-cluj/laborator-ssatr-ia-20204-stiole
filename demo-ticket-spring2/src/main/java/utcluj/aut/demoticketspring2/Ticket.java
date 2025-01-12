package utcluj.aut.demoticketspring2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {
    private String eventName;
    private String eventDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Ticket(String eventName, String eventDate, Long id) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.id = id;
    }

    public Ticket() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
