/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.utcluj.ssatr.airticketreservationapp.model;

import java.util.Objects;

/**
 *
 * @author mihai
 */
public class FlightReservation {
    private int idreservation;
    private String flightNumber;
    private int noOfTickets;
    private String userId;

    public FlightReservation(int idreservation, String flightNumber, int noOfTickets, String userId) {
        this.idreservation = idreservation;
        this.flightNumber = flightNumber;
        //this.noOfTickets = noOfTickets;
        this.noOfTickets = 1;
        this.userId = userId;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightReservation that = (FlightReservation) o;
        return idreservation == that.idreservation && noOfTickets == that.noOfTickets && Objects.equals(flightNumber, that.flightNumber) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idreservation, flightNumber, noOfTickets, userId);
    }

    @Override
    public String toString() {
        return "FlightReservation{" +
                "idreservation=" + idreservation +
                ", flightNumber='" + flightNumber + '\'' +
                ", noOfTickets=" + noOfTickets +
                ", userId='" + userId + '\'' +
                '}';
    }
}
