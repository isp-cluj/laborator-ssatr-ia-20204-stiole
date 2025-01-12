package ssatr.lab1.exercise4;

import com.google.zxing.NotFoundException;

import java.io.IOException;

public class Exercise4 {
    public static void main(String[] args) throws NotFoundException, IOException {
        TicketsManager tm = new TicketsManager();

        Ticket t1 = tm.generateTicket("Event1", 1);
        Ticket t2 = tm.generateTicket("Event1", 2);
        Ticket t3 = tm.generateTicket("Event1", 3);
        Ticket t4 = tm.generateTicket("Event1", 4);


        tm.checkinTicket("ticket.png","...");
    }
}
