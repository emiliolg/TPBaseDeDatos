package biblioteca.dao.jdbc;

import biblioteca.dao.interfaces.UserDAO;
import biblioteca.model.User;

import java.sql.*;

public class JdbcUser extends JdbcBase<User, String> implements UserDAO {

    public JdbcUser(Connection connection) {
        super(connection);
    }

    @Override
    String getTableName() {
        return "user";
    }

    @Override
    String getPKColumnName() {
        return "user_name";
    }

    @Override
    User toModel(ResultSet rs) throws SQLException {
        final User user = new User(rs.getString(getPKColumnName()));
        user.setPassword(rs.getString("password"));
        user.setDni(rs.getInt("dni"));
        user.setAdmin(rs.getBoolean("admin"));
        return user;
    }



    @Override
    public boolean insert(User user) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO user(user_name, password, admin, dni) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isAdmin());
            ps.setInt(4, user.getDni());
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String id, User user) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE user set password = ? WHERE user_name = ?")) {
            ps.setString(1, user.getPassword());
            ps.setString(2, id);
            return executeUpdate(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
