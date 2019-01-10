package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DBConnection.MySQLConnection;
import MessagingApp.Entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static MessagingApp.DAO.MySQLDAO.MySQLHelper.SQLDeleteById;

public class MySQLMessageDAO implements MessageDAO {

    private static final String SQL_MESSAGE_SELECT_ALL            = "SELECT * FROM messages";
    private static final String SQL_MESSAGE_SELECT_BY_ID          = "SELECT * FROM messages WHERE id = ?";
    private static final String SQL_MESSAGE_SELECT_ALL_BY_USER_ID = "SELECT * FROM messages WHERE sender_id = ? OR receiver_id = ?";
    //    private static final String SQL_MESSAGE_SELECT_ALL_BETWEEN_2_USERS = "SELECT * FROM messages WHERE " +
//            "(sender_id = ? and receiver_id = ?) or (sender_id = ? and receiver_id = ?) order by date_time";
    private static final String SQL_MESSAGE_INSERT                = "INSERT INTO messages (subject,body,sender_id,receiver_id) VALUES(?,?,?,?)";
    private static final String SQL_MESSAGE_UPDATE                = "UPDATE messages SET message_body = ? WHERE id = ?";
    private static final String SQL_MESSAGE_DELETE                = "DELETE FROM messages WHERE id = ?";


    @Override
    public Message getMessage(long messageId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_SELECT_BY_ID)) {

            pstmt.setLong(1, messageId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractMessageFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* private method to process a ResultSet returning a Message object */
    private Message extractMessageFromResultSet(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setMessageSubject(rs.getString("subject"));
        message.setMessageBody(rs.getString("body"));
        message.setMessageDateTime(rs.getTimestamp("date"));
        message.setSenderId(rs.getLong("sender_id"));
        message.setReceiverId(rs.getLong("receiver_id"));
        return message;
    }

    @Override
    public List<Message> getAllMessages() {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_MESSAGE_SELECT_ALL)) {

            List<Message> messagesList = new ArrayList<>();

            while (rs.next()) {
                Message message = extractMessageFromResultSet(rs);
                messagesList.add(message);
            }

            return messagesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllUserMessages(long userId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_SELECT_ALL_BY_USER_ID)) {

            pstmt.setLong(1, userId);
            pstmt.setLong(2, userId);
            ResultSet rs = pstmt.executeQuery();

            List<Message> messagesList = new ArrayList<>();

            while (rs.next()) {
                Message message = extractMessageFromResultSet(rs);
                messagesList.add(message);
            }
            return messagesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getConversation(long user1ID, long user2ID) {
/*        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_SELECT_ALL_BETWEEN_2_USERS);) {

            List<Message> conversationMessages = new ArrayList<>();
            pstmt.setLong(1, user1ID);
            pstmt.setLong(2, user2ID);
            pstmt.setLong(3, user2ID);
            pstmt.setLong(4, user1ID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = extractMessageFromResultSet(rs);
                conversationMessages.add(message);
            }

            return conversationMessages;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public long insertMessage(String messageSubject, String messageBody, long senderId, long receiverId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, messageSubject);
            pstmt.setString(2, messageBody);
            pstmt.setLong(3, senderId);
            pstmt.setLong(4, receiverId);

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
    public int updateMessage(String messageSubject, String messageBody, long messageId) {
        return 0;
    }

/*    @Override
    public int updateMessage(String messageBody, long messageId) {
        return SQLUpdateVarcharFieldById(SQL_MESSAGE_UPDATE, messageBody, messageId);
    }*/

    @Override
    public int deleteMessage(long messageId) {
        return SQLDeleteById(SQL_MESSAGE_DELETE, messageId);
    }
}
