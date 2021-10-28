package biblioteca.dao;

import biblioteca.dao.interfaces.EmpleadoDAO;
import biblioteca.dao.interfaces.TripDAO;
import biblioteca.dao.interfaces.TruckDAO;
import biblioteca.dao.interfaces.UserDAO;
import biblioteca.dao.jdbc.JdbcDriver;
import biblioteca.dao.jdbc.JdbcTrip;
import biblioteca.dao.jdbc.JdbcTruck;
import biblioteca.dao.jdbc.JdbcUser;

import java.sql.Connection;

/**
 * Singleton, se encarga de crear los DAO para todas las tablas
 * cuando pido el getinstance lo inicaliza si no estaba creado pidiendole
 * la coneccion al DBConnector
 */
public class DB {

    private final UserDAO userDAO;
    private final TruckDAO truckDAO;
    private final TripDAO tripDAO;
    private final EmpleadoDAO empleadoDAO;

    public DB(Connection connection) {
        userDAO = new JdbcUser(connection);
        truckDAO = new JdbcTruck(connection);
        tripDAO = new JdbcTrip(connection);
        empleadoDAO = new JdbcDriver(connection);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public TruckDAO getTruckDAO() {
        return truckDAO;
    }

    public TripDAO getTripDAO() {
        return tripDAO;
    }

    public EmpleadoDAO getEmpleadoDAO() {
        return empleadoDAO;
    }

    private static DB connector = null;

    //Crea la instancia de DB la cual llama a la Connector para pedirle la coneccion
    public static DB getInstance() {
        if (connector == null) {
            connector = new DB(DBConnector.getConnection());
        }
        return connector;
    }
}
