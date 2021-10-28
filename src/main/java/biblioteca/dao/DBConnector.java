package biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase Connector, establece la conexión a la Base de datos
class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "juanma";

    //Devuelve la conexión de la base de datos
    static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error al conectarse con la base de datos", ex);
        }
    }

    /**
     * Probar la Conexión
     */
    public static void main(String[] args) {
        System.out.println((DBConnector.getConnection() != null) + " conexión");
    }
}
