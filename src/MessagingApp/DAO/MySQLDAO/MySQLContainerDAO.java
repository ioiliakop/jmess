package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.ContainerDAO;
import MessagingApp.DBConnection.MySQLConnection;
import MessagingApp.Entities.Container;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;
import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLUpdateVarcharFieldById;

public class MySQLContainerDAO implements ContainerDAO {

    private static final String SQL_CONTAINER_SELECT_ALL     = "SELECT * FROM containers";
    private static final String SQL_CONTAINER_SELECT_BY_ID   = "SELECT * FROM containers WHERE id = ";
    private static final String SQL_CONTAINER_SELECT_BY_NAME = "SELECT * FROM containers WHERE container_name = ?";
    private static final String SQL_CONTAINER_INSERT         = "INSERT INTO containers (container_name) VALUES(?)";
    private static final String SQL_CONTAINER_UPDATE         = "UPDATE containers SET container_name = ? WHERE id = ?";
    private static final String SQL_CONTAINER_DELETE         = "DELETE FROM containers WHERE id = ?";


    @Override
    public Container getContainer(long containerId) {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_CONTAINER_SELECT_BY_ID + containerId)) {

            if (rs.next()) {
                return extractContainerFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Container getContainer(String containerName) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_CONTAINER_SELECT_BY_NAME)) {

            pstmt.setString(1, containerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractContainerFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Container> getAllContainers() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_CONTAINER_SELECT_ALL)) {

            ArrayList<Container> containersList = new ArrayList<>();

            while (rs.next()) {
                Container role = extractContainerFromResultSet(rs);
                containersList.add(role);
            }

            return containersList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* private method to process a ResultSet returning a container object */
    private Container extractContainerFromResultSet(ResultSet rs) throws SQLException {
        Container role = new Container();
        role.setId(rs.getLong("id"));
        role.setContainerName(rs.getString("container_name"));
        return role;
    }

    @Override
    public long insertContainer(String containerName) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_CONTAINER_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, containerName);

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
    public int updateContainer(String containerName, long containerId) {
        return SQLUpdateVarcharFieldById(SQL_CONTAINER_UPDATE, containerName, containerId);

    }

    @Override
    public int deleteContainer(long containerId) {
        return SQLDeleteById(SQL_CONTAINER_DELETE, containerId);
    }
}
