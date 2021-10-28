package biblioteca.dao.jdbc;

import biblioteca.model.Empleado;

import java.sql.*;

public class JdbcDriver extends JdbcBase<Empleado, Integer> implements biblioteca.dao.interfaces.EmpleadoDAO {

    public JdbcDriver(Connection connection) {
        super(connection);
    }

    @Override
    String getTableName() {
        return "empleados";
    }

    @Override
    String getPKColumnName() {
        return "dni";
    }

    @Override
    Empleado toModel(ResultSet rs) throws SQLException {
        final Empleado empleado = new Empleado(rs.getInt(getPKColumnName()));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setApellido(rs.getString("apellido"));
        empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        empleado.setTurno(rs.getString("turno"));
        return empleado;
    }


    //llama al procedure de alta
    @Override
    public boolean insert(Empleado driver) {
        try (PreparedStatement ps = connection.prepareCall(
                "{CALL alta_empleado(?,?,?,?,?)}")) {
            ps.setInt(1, driver.getDni());
            ps.setString(2, driver.getNombre());
            ps.setString(3, driver.getApellido());
            ps.setDate(4, Date.valueOf(driver.getFechaNacimiento()));
            ps.setString(5, driver.getTurno().toString());
            return ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
            //return false;
        }
    }

    @Override
    public boolean update(Integer id, Empleado driver) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE empleados set turno = ? WHERE dni = ?")) {
            ps.setString(1, driver.getTurno().toString());
            ps.setInt(2, id);
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
