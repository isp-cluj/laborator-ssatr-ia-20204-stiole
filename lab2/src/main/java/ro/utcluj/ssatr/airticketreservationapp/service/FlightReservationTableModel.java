package ro.utcluj.ssatr.airticketreservationapp.service;

import ro.utcluj.ssatr.airticketreservationapp.model.FlightReservation;
import ro.utcluj.ssatr.airticketreservationapp.repository.DBAccess;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class FlightReservationTableModel extends AbstractTableModel {

    private DBAccess connection;

    public FlightReservationTableModel(DBAccess connection) {
        this.connection = connection;
    }

    public void updateTable() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        try {
            return connection.findAll().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            List<FlightReservation> list = connection.findAllReservations();
            FlightReservation f = list.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return f.getIdreservation();
                case 1:
                    return f.getFlightNumber();
                case 2:
                    return f.getNoOfTickets();
                default:
                    throw new IllegalArgumentException("Invalid column index");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
