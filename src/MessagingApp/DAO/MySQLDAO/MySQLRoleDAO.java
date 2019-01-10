package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.RoleDAO;
import MessagingApp.DBConnection.MySQLConnection;
import MessagingApp.Entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;
import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLUpdateVarcharFieldById;

public class MySQLRoleDAO implements RoleDAO {

    private static final String SQL_ROLE_SELECT_ALL     = "SELECT * FROM roles";
    private static final String SQL_ROLE_SELECT_BY_ID   = "SELECT * FROM roles WHERE id = ";
    private static final String SQL_ROLE_SELECT_BY_NAME = "SELECT * FROM roles WHERE role_name = ?";
    private static final String SQL_ROLE_INSERT         = "INSERT INTO roles (role_name) VALUES(?)";
    private static final String SQL_ROLE_UPDATE         = "UPDATE roles SET role_name = ? WHERE id = ?";
    private static final String SQL_ROLE_DELETE         = "DELETE FROM roles WHERE id = ?";

    @Override
    public Role getRole(long roleId) {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_ROLE_SELECT_BY_ID + roleId)) {

            if (rs.next()) {
                return extractRoleFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getRole(String roleName) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_ROLE_SELECT_BY_NAME)) {

            pstmt.setString(1, roleName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractRoleFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_ROLE_SELECT_ALL)) {

            List<Role> rolesList = new ArrayList<>();

            while (rs.next()) {
                Role role = extractRoleFromResultSet(rs);
                rolesList.add(role);
            }

            return rolesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* private method to process a ResultSet returning a Role object */
    private Role extractRoleFromResultSet(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setRoleName(rs.getString("role_name"));
        return role;
    }

    @Override
    public long insertRole(String roleName) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_ROLE_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, roleName);

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
    public int updateRole(String roleName, long roleId) {
        return SQLUpdateVarcharFieldById(SQL_ROLE_UPDATE, roleName, roleId);

    }

    @Override
    public int deleteRole(long roleId) {
        return SQLDeleteById(SQL_ROLE_DELETE, roleId);
    }
}
