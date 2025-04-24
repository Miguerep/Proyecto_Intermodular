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
    Statement sent;

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
    public boolean vaciar() {
        boolean correcto = false;
        String sql;
        int resultado;
        try {
            sent =con.createStatement();
            sql = "DELETE FROM servicios";
            resultado = sent.executeUpdate(sql);
            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se han eliminado " + resultado + " servicios.");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error.");
        }
        return correcto;
    }
    
    public boolean añadirServicio(int id,String tipo,String duracion, String precio) {
    String sql;
    boolean correcto = false;
    int resultado;

    try {
        // Crear la sentencia SQL para insertar un nuevo servicio
        sql = "INSERT INTO servicio (id_servicio, tipo, duracion, precio) VALUES (?, ?, ?, ?)";

        // Usar PreparedStatement para evitar inyecciones SQL y manejar valores dinámicos
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, tipo);       // Tipo de la reserva
        pstmt.setInt(2, id);       // ID de la reserva
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

    public Object[][] objetenerTodo() {
        
    Object[][] tabla = null;
    ResultSet rs;
    String sql = "SELECT * FROM servicio";
    int numRegistros;
    int contador = 0;
    int idServicio;
    String tipo, duracion, precio;

    try {
        sent = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = sent.executeQuery(sql);

        // Vamos al último registro para determinar cuántos registros hay.
        rs.last();
        numRegistros = rs.getRow();

        // Damos tamaño a la matriz de objetos para almacenar lo leído de la BD.
        System.out.println("Registros obtenidos: " + numRegistros);
        tabla = new Object[numRegistros][4];

        // Recolocamos el puntero para recorrer el resultado.
        rs.beforeFirst();
        System.out.println("Lista de servicios");
        while (rs.next()) {
            // Preparamos los datos
            idServicio = rs.getInt("id_servicio");
            tipo = rs.getString("tipo");
            duracion = rs.getString("duracion");
            precio = rs.getString("precio");

            // Mostramos los datos por terminal para hacer seguimiento de la ejecución.
            System.out.println("ID Servicio: " + idServicio + "\t Tipo: " + tipo + "\t Duración: " + duracion + "\t Precio: " + precio);

            // Guardamos los datos en la tabla a devolver.
            tabla[contador][0] = idServicio;
            tabla[contador][1] = tipo;
            tabla[contador][2] = duracion;
            tabla[contador][3] = precio;

            // Avanzamos posición en el array.
            contador++;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return tabla;
}
    public boolean borrarPorNombre(String tipo) {
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sent = con.createStatement();
            sql = "DELETE FROM servicio WHERE tipo = " + "'" + tipo + "'";
            resultado = sent.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se ha borrado el servicio");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
    }
