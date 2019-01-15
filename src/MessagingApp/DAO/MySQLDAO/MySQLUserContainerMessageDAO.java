package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.MySQLConnection;
import MessagingApp.Entities.MessageFolders.Folder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserContainerMessageDAO implements UserContainerMessageDAO {

    private static final String SQL_MESSAGE_IDS_SELECT_BY_USER_CONTAINER     = "SELECT message_id FROM users_containers_messages WHERE user_id = ? AND container_id = ?";
    private static final String SQL_USER_CONTAINER_MESSAGE_INSERT            = "INSERT INTO users_containers_messages (user_id,container_id,message_id) VALUES(?,?,?)";
    private static final String SQL_MESSAGE_CONTAINER_UPDATE                 = "UPDATE users_containers_messages SET container_id = ? WHERE user_id = ? AND message_id = ?";
    private static final String SQL_ALL_MESSAGES_IN_CONTAINER_UPDATE         = "UPDATE users_containers_messages SET container_id = ? WHERE user_id = ? AND container_id = ?";
    private static final String SQL_ALL_TARGET_USER_MESSAGES_IN_INBOX_UPDATE = "UPDATE users_containers_messages SET container_id = ? WHERE user_id = ? AND container_id = 1 AND users_containers_messages.message_id = ";
    private static final String SQL_USER_CONTAINER_ALL_MESSAGES_DELETE       = "DELETE FROM users_containers_messages WHERE user_id = ? AND container_id= ?";
    private static final String SQL_USER_CONTAINER_MESSAGE_DELETE            = "DELETE FROM users_containers_messages WHERE message_id = ?";


    @Override
    public List<Long> getUserContainerMessages(long userId, Folder container) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_IDS_SELECT_BY_USER_CONTAINER)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, container.ID());
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
    public long insertUserContainerMessage(long userId, Folder container, long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_USER_CONTAINER_MESSAGE_INSERT)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, container.ID());
            pstmt.setLong(3, messageId);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted == 1) return rowsInserted;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

/*    public long insertUserContainerMessage(long userId, long containerId, long messageId) {
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
    }*/

    @Override
    public int updateUserContainerMessage(long userId, Folder container, long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_CONTAINER_UPDATE)) {

            pstmt.setLong(1, container.ID());
            pstmt.setLong(2, userId);
            pstmt.setLong(3, messageId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 1) return rowsUpdated;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateAllUserContainerMessages(Folder originalContainer, long userId, Folder targetContainer) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_ALL_MESSAGES_IN_CONTAINER_UPDATE)) {

            pstmt.setLong(1, targetContainer.ID());
            pstmt.setLong(2, userId);
            pstmt.setLong(3, originalContainer.ID());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) return rowsUpdated;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUserAllContainerMessages(long userId, Folder container) {
        return 0;
    }

    @Override
    public int deleteUserContainerMessage(long userId, Folder container, long messageId) {
        return 0;
    }
}
