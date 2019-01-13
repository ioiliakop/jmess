package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DAO.MessageReceiversDAO;

import java.util.List;

public class MySQLMessageReceiversDAO implements MessageReceiversDAO {

    @Override
    public List<Long> getMessageReceivers(long messageId) {
        return null;
    }

    @Override
    public int insertMessageReceivers(long messageId, long receiverId) {
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
