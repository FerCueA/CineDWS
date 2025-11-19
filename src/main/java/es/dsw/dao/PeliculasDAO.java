package es.dsw.dao;

import es.dsw.models.PeliculasBD;
import es.dsw.connections.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculasDAO {
    public List<PeliculasBD> obtenerPeliculasActivas() {
        // Lista para almacenar las películas
        List<PeliculasBD> lista = new ArrayList<>();
        // Consulta SQL para obtener las películas activas con sus campos de la base de
        // datos
        String sql = "SELECT IDFILM_RF, TITLE_RF, SYNOPSIS_RF, DIRECTOR_RF, REPARTO_RF, ANIO_RF, DATEPREMIERE_RF FROM REPOSITORY_FILM WHERE S_ACTIVEROW_RF = 1";

        // Crear una instancia de la conexión a la base de datos generando el objeto
        MySqlConnection conexion = new MySqlConnection();
        // Hay que ejecutar el open dentro de un try-catch para manejar posibles
        // excepciones
        try {
            // Abrir la conexión a la base de datos para poder ejecutar la consulta
            conexion.open();
            // bloque de código para ejecutar la consulta y procesar los resultados
            Connection conn = conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // bluque para recorrer los resultados de la consulta
            while (rs.next()) {
                PeliculasBD peli = new PeliculasBD(
                        rs.getInt("IDFILM_RF"),
                        rs.getString("TITLE_RF"),
                        rs.getString("SYNOPSIS_RF"),
                        rs.getString("DIRECTOR_RF"),
                        rs.getString("REPARTO_RF"),
                        rs.getInt("ANIO_RF"),
                        rs.getString("DATEPREMIERE_RF"));
                // Agregar la película a la lista despues de cada interación
                lista.add(peli);
            }
            // Cerrar los recursos abiertos
            rs.close();
            stmt.close();
            conn.close();
            // capturamos posibles excepciones en "e"
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
