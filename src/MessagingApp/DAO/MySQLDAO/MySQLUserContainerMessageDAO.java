package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.DBConnection.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserContainerMessageDAO implements UserContainerMessageDAO {

    private static final String SQL_MESSAGE_IDS_SELECT_BY_USER_CONTAINER = "SELECT message_id FROM users_containers_messages WHERE user_id = ? AND container_id = ?";
//    private static final String SQL_ROLE_SELECT_BY_NAME                  = "SELECT * FROM users_containers_messages WHERE role_name = ?";
    private static final String SQL_USER_CONTAINER_MESSAGE_INSERT        = "INSERT INTO users_containers_messages (user_id,container_id,message_id) VALUES(?,?,?)";
    private static final String SQL_MESSAGE_CONTAINER_UPDATE             = "UPDATE users_containers_messages SET message_id = ? WHERE user_id = ? AND container_id = ?";
    private static final String SQL_ROLE_DELETE                          = "DELETE FROM users_containers_messages WHERE message_id = ?";


    @Override
    public List<Long> getUserContainerMessages(long userId, long containerId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_IDS_SELECT_BY_USER_CONTAINER)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, containerId);
            ResultSet rs = pstmt.executeQuery();

            List<Long> messageIdsList = new ArrayList<>();

            while (rs.next()) {
                long messageId = rs.getLong("message_id");
                messageIdsList.add(messageId);
            }

            return messageIdsList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

/*    private long extractMessageIdFromResultSet(ResultSet rs) throws SQLException {
        long messageId;
        role.setId(rs.getLong("id"));
        role.setRoleName(rs.getString("role_name"));
        return role;
    }*/

    @Override
    public long insertUserContainerMessage(long userId, long containerId, long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_CONTAINER_MESSAGE_INSERT)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, containerId);
            pstmt.setLong(3, messageId);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted==1) return rowsInserted;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUserContainerMessage(long userId, long containerId, long messageId) {
        return 0;
    }

    @Override
    public int deleteUserContainerMessage(long userId, long containerId, long messageId) {
        return 0;
    }
}
