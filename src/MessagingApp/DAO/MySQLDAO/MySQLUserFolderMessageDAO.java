package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.MySQLConnection;
import MessagingApp.Entities.MessageFolders.Folder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserFolderMessageDAO implements UserFolderMessageDAO {

    private static final String SQL_SELECT_MESSAGE_IDS_BY_USER_FOLDER           = "SELECT message_id FROM users_folders_messages WHERE user_id = ? AND folder_id = ?";
    private static final String SQL_SELECT_COUNT_UNREAD_MESSAGES_IN_USER_FOLDER = "SELECT COUNT(message_id) FROM users_folders_messages WHERE user_id = ? AND folder_id = ? AND is_read = 0";
    private static final String SQL_SELECT_MESSAGE_IDS_IN_USER_FOLDER_SENT_BY   = "SELECT DISTINCT message_id FROM users_folders_messages, messages WHERE user_id = ? AND folder_id = ? AND sender_id = ?";
    private static final String SQL_INSERT_USER_FOLDER_MESSAGE                  = "INSERT INTO users_folders_messages (user_id,folder_id,message_id) VALUES(?,?,?)";
    private static final String SQL_UPDATE_SPECIFIC_MESSAGE_FOLDER              = "UPDATE users_folders_messages SET folder_id = ? WHERE user_id = ? AND message_id = ?";
    private static final String SQL_UPDATE_MESSAGE_IN_FOLDER_AS_READ            = "UPDATE users_folders_messages SET is_read = 1 WHERE user_id = ? AND folder_id = ?";
    private static final String SQL_UPDATE_ALL_MESSAGES_IN_USER_FOLDER          = "UPDATE users_folders_messages SET folder_id = ? WHERE user_id = ? AND folder_id = ?";
    private static final String SQL_UPDATE_ALL_MESSAGES_IN_FOLDER_SENT_BY       = "UPDATE users_folders_messages, messages SET folder_id = ? WHERE user_id = ? AND folder_id = ? AND sender_id = ?";
    private static final String SQL_DELETE_ALL_MESSAGES_IN_USER                 = "DELETE FROM users_folders_messages WHERE user_id = ? AND folder_id = ?";
    private static final String SQL_USER_FOLDER_MESSAGE_DELETE                  = "DELETE FROM users_folders_messages WHERE message_id = ?";


    @Override
    public List<Long> getUserFolderMessageIDs(long userId, Folder folder) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_MESSAGE_IDS_BY_USER_FOLDER)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, folder.ID());
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

    @Override
    public List<Long> getMessageIDsInFolderSentByUser(User owner, Folder folder, User sender) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_MESSAGE_IDS_IN_USER_FOLDER_SENT_BY)) {

            pstmt.setLong(1, owner.getId());
            pstmt.setLong(2, folder.ID());
            pstmt.setLong(3, sender.getId());
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

    @Override
    public long getUnreadMessagesCountInFolder(User owner, Folder folder) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_COUNT_UNREAD_MESSAGES_IN_USER_FOLDER)) {

            pstmt.setLong(1, owner.getId());
            pstmt.setLong(2, folder.ID());
            ResultSet rs = pstmt.executeQuery();

            long numberOfUnreadMessages = 0;
            if (rs.next()) {
                numberOfUnreadMessages = rs.getLong("COUNT(message_id)");
            }
            return numberOfUnreadMessages;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public long insertUserFolderMessage(long userId, Folder folder, long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_USER_FOLDER_MESSAGE)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, folder.ID());
            pstmt.setLong(3, messageId);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted == 1) return rowsInserted;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUserFolderMessage(long userId, Folder folder, long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_SPECIFIC_MESSAGE_FOLDER)) {

            pstmt.setLong(1, folder.ID());
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
    public long updateUserFolderMessagesAsRead(User user, Folder folder) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_MESSAGE_IN_FOLDER_AS_READ)) {

            pstmt.setLong(1, user.getId());
            pstmt.setLong(2, folder.ID());
            long rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public long updateUserFolderMessagesSentBy(User owner, Folder originalFolder, Folder targetFolder, User sender) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ALL_MESSAGES_IN_FOLDER_SENT_BY)) {

            pstmt.setLong(1, targetFolder.ID());
            pstmt.setLong(2, owner.getId());
            pstmt.setLong(3, originalFolder.ID());
            pstmt.setLong(4, sender.getId());

            long rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) return rowsUpdated;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    @Override
    public int updateAllUserFolderMessages(Folder originalFolder, long userId, Folder targetFolder) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ALL_MESSAGES_IN_USER_FOLDER)) {

            pstmt.setLong(1, targetFolder.ID());
            pstmt.setLong(2, userId);
            pstmt.setLong(3, originalFolder.ID());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) return rowsUpdated;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUserAllFolderMessages(long userId, Folder folder) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_ALL_MESSAGES_IN_USER)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, folder.ID());

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUserFolderMessage(long userId, Folder folder, long messageId) {
        return 0;
    }
}
