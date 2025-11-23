package es.dsw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import es.dsw.models.SesionSala;
import es.dsw.connections.MySqlConnection;

public class SesionesSalasDAO {
    public List<SesionSala> getSesionesDisponibles() {
        List<SesionSala> sesiones = new ArrayList<>();
        MySqlConnection conn = new MySqlConnection();
        conn.open();
        if (conn.isError() || conn.getConnection() == null) {
            System.err.println("Error de conexión a la base de datos: " + conn.msgError());
            conn.close();
            return sesiones; // Retorna lista vacía si hay error
        }
        String query = "SELECT NUMBERROOM_RCF AS NUMSALA, IDFILM_SSF AS IDPELICULA, IDSESSION_SSF AS IDSESION " +
                "FROM DB_FILMCINEMA.SESSION_FILM, DB_FILMCINEMA.ROOMCINEMA_FILM " +
                "WHERE S_ACTIVEROW_SSF = 1 AND IDROOMCINEMA_RCF = IDROOMCINEMA_SSF AND S_ACTIVEROW_RCF = 1 " +
                "ORDER BY NUMBERROOM_RCF ASC";
        try (Connection connection = conn.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SesionSala sesion = new SesionSala();
                sesion.setNumSala(rs.getInt("NUMSALA"));
                sesion.setIdPelicula(rs.getInt("IDPELICULA"));
                sesion.setIdSesion(rs.getInt("IDSESION"));
                sesiones.add(sesion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return sesiones;
    }

    public String getTituloPeliculaPorId(int idPelicula) {
        String titulo = null;
        MySqlConnection conn = new MySqlConnection();
        conn.open();
        String query = "SELECT TITLE_RF AS PELICULA FROM DB_FILMCINEMA.REPOSITORY_FILM WHERE IDFILM_RF = " + idPelicula;
        try (Connection connection = conn.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                titulo = rs.getString("PELICULA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return titulo;
    }
}
