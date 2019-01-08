package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.UserDAO;
import MessagingApp.DBConnection.MySQLConnection;
import MessagingApp.Entities.User;

import java.sql.*;
import java.util.ArrayList;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;

public class MySQLUserDAO implements UserDAO {

    private static final String SQL_USER_SELECT_ALL          = "SELECT * FROM users";
    private static final String SQL_USER_SELECT_BY_ID        = "SELECT * FROM users WHERE id = ";
    private static final String SQL_USER_SELECT_BY_NAME      = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_USER_SELECT_BY_NAME_PASS = "SELECT * FROM users WHERE username = ? AND password = SHA(?)";
    private static final String SQL_USER_INSERT              = "INSERT INTO users(username,password,role_id) VALUES(?,SHA(?),?)";
    private static final String SQL_USER_UPDATE              = "UPDATE users SET username = ?, password = SHA(?), role_id = ? WHERE id = ?";
    private static final String SQL_USER_DELETE              = "DELETE FROM users WHERE id = ?";


    @Override
    public User getUser(long id) {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_USER_SELECT_BY_ID + id)) {

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_NAME)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_USER_SELECT_ALL)) {

            ArrayList<User> usersList = new ArrayList<>();

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                usersList.add(user);
            }

            return usersList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_NAME_PASS)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* private method to process a ResultSet returning a User object */
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoleId(rs.getLong("role_id"));
        return user;
    }

    @Override
    public long insertUser(String username, String password, long roleId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setLong(3, roleId);

            int insertedRows = pstmt.executeUpdate();
            if (insertedRows == 1) {
                // We get the generated key from the resultSet below
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public long insertUser(String username, String password) {
        return insertUser(username, password, 1);
    }


    @Override
    public int updateUser(String username, String password, long roleId, long id) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_UPDATE)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setLong(3, roleId);
            pstmt.setLong(4, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 1) {
                return rowsUpdated;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUser(long id) {
        return SQLDeleteById(SQL_USER_DELETE, id);
    }
}
