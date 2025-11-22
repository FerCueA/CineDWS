package es.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import es.dsw.connections.MySqlConnection;

public class BuyTicketsDAO {
    public boolean insertarCompra(String nombre, String apellidos, String email, String titularTarjeta,
            String numeroTarjeta,
            String mesTarjeta, String anioTarjeta, String ccs, float totalPrecio) {
        String sql = "INSERT INTO DB_FILMCINEMA.BUYTICKETS_FILM " +
                "(IDBUYTICKETS_BTF, NAME_BTF, SURNAMES_BTF, EMAIL_BTF, CARDHOLDER_BTF, CARDNUMBER_BTF, MONTHCARD_BTF, YEARCARD_BTF, "
                +
                "CCS_CARD_CODE_BTF, TOTALPRICE_BTF, S_ACTIVEROW_BTF, S_IDUSER_BTF) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, b'1', 1)";
        MySqlConnection myConn = new MySqlConnection();
        myConn.open();
        try (Connection conn = myConn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, titularTarjeta);
            ps.setString(5, numeroTarjeta);
            ps.setString(6, mesTarjeta);
            ps.setString(7, anioTarjeta);
            ps.setString(8, ccs);
            ps.setFloat(9, totalPrecio);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
