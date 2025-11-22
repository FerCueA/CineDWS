package es.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import es.dsw.connections.MySqlConnection;

public class TicketDAO {
    public boolean insertarTicket(int idSession, String fechaSesion, String horaSesion, String serialCode,
            boolean younger, float precio, String rowSeat, int idBuyTickets) {
        String sql = "INSERT INTO DB_FILMCINEMA.TICKET_FILM " +
                "(IDTICKET_TKF, IDSESSION_TKF, DATESESSION_TKF, TIMESESSION_TKF, SERIALCODE_TKF, YOUNGER_TKF, PRICE_TKF, ROWSEAT_TKF, "
                +
                "IDBUYTICKETS_TKF, S_ACTIVEROW_TKF, S_IDUSER_TKF) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, b'1', 1)";
        MySqlConnection myConn = new MySqlConnection();
        myConn.open();
        try (Connection conn = myConn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSession);
            ps.setString(2, fechaSesion);
            ps.setString(3, horaSesion);
            ps.setString(4, serialCode);
            ps.setBoolean(5, younger);
            ps.setFloat(6, precio);
            ps.setString(7, rowSeat);
            ps.setInt(8, idBuyTickets);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            myConn.close();
        }
    }
}
