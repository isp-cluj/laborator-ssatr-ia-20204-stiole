package ssatr.lab1.exercise4;

import java.util.ArrayList;
import java.util.List;

public class Exercise4 {
    public static void main(String[] args) throws Exception {
        TicketsManager ticketsManager = new TicketsManager();
        List<Ticket> ticketsList = new ArrayList<>();

        Ticket ticket1 = ticketsManager.generateTicket("event1", "buyer1", 2.3);
        Ticket ticket2 = ticketsManager.generateTicket("event2", "buyer2", 5.0);
        Ticket ticket3 = ticketsManager.generateTicket("event3", "buyer3", 3.2);
        Ticket ticket4 = ticketsManager.generateTicket("event4", "buyer4", 8.6);

        ticketsList.add(ticket1);
        ticketsList.add(ticket2);
        ticketsList.add(ticket3);
        ticketsList.add(ticket4);

        int buyerCount = 0;

        for(Ticket ticket : ticketsList) {
            ticketsManager.generateTicketQr(ticket);
            buyerCount++;
            ticketsManager.generateTicketPdf(ticket, "C:\\Users\\catal\\Desktop\\Master_IA_2\\SSATR\\laborator_1\\ticket_buyer" + buyerCount +".png", buyerCount);
        }

    }
}
