package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.MessageReceiversDAO;
import MessagingApp.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLMessageReceiversDAO implements MessageReceiversDAO {

    private static final String SQL_MESSAGE_RECEIVER_INSERT = "INSERT INTO message_receivers (message_id,receiver_id) VALUES(?,?)";


    @Override
    public List<Long> getMessageReceivers(long messageId) {
        return null;
    }

    @Override
    public int insertMessageReceivers(long messageId, long receiverId) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_MESSAGE_RECEIVER_INSERT)) {

            pstmt.setLong(1, messageId);
            pstmt.setLong(2, receiverId);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted == 1) return rowsInserted;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateMessageReceivers(long messageId, long receiverId) {
        return 0;
    }

    @Override
    public int deleteMessageReceivers(long messageId, long receiverId) {
        return 0;
    }
}
