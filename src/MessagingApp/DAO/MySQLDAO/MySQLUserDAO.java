package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.UserDAO;
import MessagingApp.MySQLConnection;
import MessagingApp.Entities.Statuses.Status;
import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;
import static MessagingApp.Entities.Roles.Role.USER;

public class MySQLUserDAO implements UserDAO {

    private static final String SQL_USER_SELECT_ALL          = "SELECT * FROM users";
    private static final String SQL_USER_SELECT_BY_STATUS    = "SELECT * FROM users WHERE status_id = ?";
    private static final String SQL_USER_SELECT_BY_ID        = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_USER_SELECT_BY_NAME      = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_USER_SELECT_BY_NAME_PASS = "SELECT * FROM users WHERE username = ? AND password = ? AND status_id = 1";
    private static final String SQL_USER_INSERT              = "INSERT INTO users(username,password,role_id) VALUES(?,?,?)";
    private static final String SQL_USER_UPDATE              = "UPDATE users SET username = ?, password = ?, role_id = ?, status_id = ? WHERE id = ?";
    private static final String SQL_USER_DELETE              = "DELETE FROM users WHERE id = ?";


    @Override
    public User getUser(long userId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_ID)) {

            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return MySQLHelper.extractUserFromResultSet(rs);
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
                return MySQLHelper.extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getActiveUserByUsernameAndPassword(String username, String password) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_NAME_PASS)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return MySQLHelper.extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_USER_SELECT_ALL)) {

            List<User> usersList = new ArrayList<>();

            while (rs.next()) {
                User user = MySQLHelper.extractUserFromResultSet(rs);
                usersList.add(user);
            }

            return usersList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsersByStatus(Status status) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_STATUS)) {

            pstmt.setLong(1, status.ID());
            ResultSet rs = pstmt.executeQuery();

            List<User> usersList = new ArrayList<>();

            while (rs.next()) {
                User user = MySQLHelper.extractUserFromResultSet(rs);
                usersList.add(user);
            }
            return usersList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public long insertUser(String username, String password, Role role) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setLong(3, role.ID());

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
        return insertUser(username, password, USER);
    }

    @Override
    public int updateUser(User user) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_UPDATE)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setLong(3, user.getRoleId());
            pstmt.setLong(4, user.getStatusId());
            pstmt.setLong(5, user.getId());

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
    public int deleteUser(User user) {
        return SQLDeleteById(SQL_USER_DELETE, user.getId());
    }

}
