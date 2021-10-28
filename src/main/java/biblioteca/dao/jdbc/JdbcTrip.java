package biblioteca.dao.jdbc;

import biblioteca.dao.interfaces.TripDAO;
import biblioteca.model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTrip extends JdbcBase<Trip, Integer> implements TripDAO {


    public JdbcTrip(Connection connection) {
        super(connection);
    }

    @Override
    String getTableName() {
        return "trip";
    }

    @Override
    String getPKColumnName() {
        return "trip_id";
    }

    @Override
    Trip toModel(ResultSet rs) throws SQLException {
        final Trip trip = new Trip(rs.getInt(getPKColumnName()));
        trip.setTripId(rs.getInt("trip_id"));
        trip.setDriverDni(rs.getInt("driver_dni"));
        trip.setTruckPlate(rs.getInt("truck_plate_number"));
        trip.setFrom(rs.getString("from_location"));
        trip.setTo(rs.getString("to_location"));
        trip.setStart(rs.getDate("start").toLocalDate());
        trip.setEnd(rs.getDate("end").toLocalDate());
        return trip;
    }

    @Override //recibe el dni del conductor y lista sus viajes
    public List<Trip> listDriverTrips(int driverDNI) {
        final List<Trip> result = new ArrayList<>();
        try {
            final PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM trip WHERE driver_dni = ?");
            ps.setInt(1, driverDNI);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) result.add(toModel(rs));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Trip trip) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO trip(trip_id,driver_dni,plate_number, from_location, to_location, start, end)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)")
        ) {
            ps.setInt(1, trip.getTripId());
            ps.setInt(2, trip.getDriverDni());
            ps.setInt(3, trip.getTruckPlate());
            ps.setString(4, trip.getFrom().name());
            ps.setString(5, trip.getTo().name());
            ps.setDate(6, Date.valueOf(trip.getStart()));
            ps.setDate(7, Date.valueOf(trip.getEnd()));
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Integer id, Trip trip) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE trip SET  end = ?  WHERE trip_id = ?")
        ) {
            ps.setDate(1, Date.valueOf(trip.getEnd()));
            ps.setInt(2, id);
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
