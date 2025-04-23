package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Servicio;

public class ControladorServicios {
    private Connection con;
    private String usuario = "usuario";
    private String clave = "96WFjTsdglPkS!R(";
    private String url = "jdbc:mysql://192.168.0.30/proyecto_final";

    public ControladorServicios() {
        conectarBD();
    }

    // Método para conectar a la base de datos
    public void conectarBD() {
        try {
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión establecida con " + url);
        } catch (SQLException e) {
            System.err.println("SQL Error mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    // Método para desconectar de la base de datos
    public void desconectarBD() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Método para insertar un nuevo servicio en la base de datos.
     *
     * @param tipo     Tipo de servicio
     * @param duracion Duración del servicio
     * @param precio   Precio del servicio
     */
    public void insertarServicio(String tipo, String duracion, String precio) {
        String sql = "INSERT INTO SERVICIO (tipo, duracion, precio) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            pstmt.setString(2, duracion);
            pstmt.setString(3, precio);
            pstmt.executeUpdate();
            System.out.println("Servicio insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el servicio: " + e.getMessage());
        }
    }

    /**
     * Método para recuperar todos los servicios de la base de datos.
     *
     * @return Lista de servicios
     */
    public List<Servicio> obtenerServicios() {
        List<Servicio> listaServicios = new ArrayList<>();
        String sql = "SELECT id, tipo, duracion, precio FROM SERVICIO";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String duracion = rs.getString("duracion");
                String precio = rs.getString("precio");
                listaServicios.add(new Servicio(id, tipo, duracion, precio));
            }
        } catch (SQLException e) {
            System.err.println("Error al recuperar los servicios: " + e.getMessage());
        }
        return listaServicios;
    }
    public boolean añadirServicio(int id,String tipo,String duracion, String precio) {
    String sql;
    boolean correcto = false;
    int resultado;

    try {
        // Crear la sentencia SQL para insertar una nueva reserva
        sql = "INSERT INTO Servicios (id_servicio, tipo, duracion, precio) VALUES (?, ?, ?, ?)";

        // Usar PreparedStatement para evitar inyecciones SQL y manejar valores dinámicos
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, tipo);       // ID de la reserva
        pstmt.setInt(2, id);       // ID del cliente
        pstmt.setString(3, duracion);      // ID del servicio
        pstmt.setString(4, precio);        // Fecha de la reserva
               // Estado de la reserva

        // Ejecutar la consulta
        resultado = pstmt.executeUpdate();

        // Verificar si la inserción fue exitosa
        if (resultado > 0) {
            correcto = true;
            System.out.println("Se ha insertado el servicio correctamente.");
        } else {
            System.out.println("No se pudo insertar el servicio.");
        }

        // Cerrar la sentencia
        pstmt.close();
    } catch (SQLException e) {
        // Manejar errores de SQL
        System.err.println("Error al insertar el servicio: " + e.getMessage());
    }

    return correcto;
}
}