package MessagingApp.DAO.MySQLDAO;

import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class MySQLHelper {

    static int SQLDeleteById(String SQLString, long id){
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLString)) {

            pstmt.setLong(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 1) {
                return rowsDeleted;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /* helper method to process a ResultSet returning a USER object */
    static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoleId(rs.getLong("role_id"));
        user.setStatusId(rs.getLong("status_id"));
        return user;
    }

    /* helper method to process a ResultSet returning a Message object */
    static Message extractMessageFromResultSet(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setMessageSubject(rs.getString("subject"));
        message.setMessageBody(rs.getString("body"));
        message.setMessageDateCreated(rs.getTimestamp("date_created"));
        message.setSenderId(rs.getLong("sender_id"));
        return message;
    }

    static List<Message> getMessagesWithUserParam(User user, String SQLString) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLString)) {

            pstmt.setLong(1, user.getId());
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

}
