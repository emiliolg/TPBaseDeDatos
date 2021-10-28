package biblioteca.dao.jdbc;

import biblioteca.dao.interfaces.TruckDAO;
import biblioteca.model.Truck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTruck extends JdbcBase<Truck, Integer> implements TruckDAO {

    public JdbcTruck(Connection connection) {
        super(connection);
    }

    @Override
    String getTableName() {
        return "truck";
    }

    @Override
    String getPKColumnName() {
        return "plate_number";
    }

    @Override
    Truck toModel(ResultSet rs) throws SQLException {
        final Truck truck = new Truck(rs.getInt(getPKColumnName()));
        truck.setBrand(rs.getString("brand"));
        truck.setModel(rs.getString("model"));
        truck.setMaxTons(rs.getInt("max_tons"));
        truck.setTankCapacity(rs.getInt("tank_capacity"));
        truck.setConsumption(rs.getInt("consumption"));
        return truck;
    }

    @Override
    public boolean insert(Truck truck) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO truck(plate_number, brand, model, max_tons, tank_capacity, consumption) VALUES (?, ?, ?, ?,? ,?)")
        ) {
            ps.setInt(1, truck.getPlateNumber());
            ps.setString(2, truck.getBrand());
            ps.setString(3, truck.getModel());
            ps.setInt(4, truck.getMaxTons());
            ps.setInt(5, truck.getTankCapacity());
            ps.setInt(6, truck.getConsumption());
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Integer id, Truck truck) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE truck SET tank_capacity = ?  WHERE plate_number = ?")
        ) {
            ps.setInt(1, truck.getTankCapacity());
            ps.setInt(2, id);
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
