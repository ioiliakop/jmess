package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.MySQLConnection;
import MessagingApp.Entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;
import static MessagingApp.DAO.MySQLDAO.MySQLHelper.getMessagesWithUserParam;

public class MySQLMessageDAO implements MessageDAO {

    private static final String SQL_MESSAGE_SELECT_ALL              = "SELECT * FROM messages";
    private static final String SQL_MESSAGE_SELECT_BY_ID            = "SELECT * FROM messages WHERE id = ?";
    private static final String SQL_MESSAGE_SELECT_ALL_SENT_BY_USER = "SELECT * FROM messages WHERE sender_id = ?";
    private static final String SQL_MESSAGE_SELECT_ALL_SENT_TO_USER = "SELECT DISTINCT id, subject, body, date_created, sender_id FROM messages, message_receivers WHERE id = message_id AND receiver_id = ?";
    private static final String SQL_MESSAGE_SELECT_ALL_OF_USER      = "SELECT DISTINCT id, subject, body, date_created, sender_id FROM messages, message_receivers WHERE id = message_id AND (sender_id = ? OR receiver_id = ?)";
    private static final String SQL_MESSAGE_INSERT                  = "INSERT INTO messages (subject,body,sender_id) VALUES(?,?,?)";
    private static final String SQL_MESSAGE_UPDATE                  = "UPDATE messages SET subject = ?, body = ? WHERE id = ?";
    private static final String SQL_MESSAGE_DELETE                  = "DELETE FROM messages WHERE id = ?";


    @Override
    public Message getMessage(long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_SELECT_BY_ID)) {

            pstmt.setLong(1, messageId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return MySQLHelper.extractMessageFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_MESSAGE_SELECT_ALL)) {

            List<Message> messagesList = new ArrayList<>();

            while (rs.next()) {
                Message message = MySQLHelper.extractMessageFromResultSet(rs);
                messagesList.add(message);
            }

            return messagesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Message> getMessagesSentByUser(User sender) {
        return getMessagesWithUserParam(sender, SQL_MESSAGE_SELECT_ALL_SENT_BY_USER);
    }

    @Override
    public List<Message> getMessagesSentToUser(User receiver) {
        return getMessagesWithUserParam(receiver, SQL_MESSAGE_SELECT_ALL_SENT_TO_USER);
    }

    @Override
    public List<Message> getMessagesOfUser(User senderOrReceiver) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_SELECT_ALL_OF_USER)) {

            pstmt.setLong(1, senderOrReceiver.getId());
            pstmt.setLong(2, senderOrReceiver.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Message> messagesList = new ArrayList<>();

            while (rs.next()) {
                Message message = MySQLHelper.extractMessageFromResultSet(rs);
                messagesList.add(message);
            }
            return messagesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public long insertMessage(String messageSubject, String messageBody, long senderId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, messageSubject);
            pstmt.setString(2, messageBody);
            pstmt.setLong(3, senderId);

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
    public int updateMessage(Message message) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_UPDATE)) {

            pstmt.setString(1, message.getMessageSubject());
            pstmt.setString(2, message.getMessageBody());
            pstmt.setLong(3, message.getId());

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
    public int deleteMessage(Message message) {
        return SQLDeleteById(SQL_MESSAGE_DELETE, message.getId());
    }

}
