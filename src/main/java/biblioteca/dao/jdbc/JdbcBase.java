package biblioteca.dao.jdbc;

import biblioteca.dao.interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


//Clase base para los jdbc, implementan los metodos de la interface CRUD mas genericos
public abstract class JdbcBase<T, PK> implements CRUD<T, PK> {

    protected final Connection connection;

    public JdbcBase(Connection connection) {
        this.connection = connection;
    }

    abstract String getTableName();

    abstract String getPKColumnName();


    abstract T toModel(ResultSet rs) throws SQLException;

    @Override
    public T find(PK id) {
        // ejemplo: "select * from truck where plate_number = " + id
        // "select * from " + getTableName() + " where " + getPKColumnName() + " = ?"
        try (PreparedStatement ps = connection.prepareStatement(
                format("SELECT * FROM %s WHERE %s = ?", getTableName(), getPKColumnName()))) {
            ps.setObject(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) return toModel(rs);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> list() {
        final List<T> result = new ArrayList<>();
        try (final Statement ps = connection.createStatement();
             final ResultSet rs = ps.executeQuery(format("SELECT * FROM %s", getTableName()))) {
            while (rs.next())
                result.add(toModel(rs));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(PK id) {
        try (PreparedStatement ps = connection.prepareStatement(
                format("DELETE FROM %s WHERE %s = ?", getTableName(), getPKColumnName()))) {
            ps.setObject(1, id);
            return executeUpdate(ps);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
//Ejecuta el update de la base de datos, si todó esta bien devuelve 1
    boolean executeUpdate(PreparedStatement ps) throws SQLException {
        int i = ps.executeUpdate();
        return i == 1;
    }
}
