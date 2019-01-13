package MessagingApp.DAO;

import java.util.List;

public interface MessageReceiversDAO {

    List<Long> getMessageReceivers(long messageId);

    int insertMessageReceivers(long messageId, long receiverId);

    int updateMessageReceivers(long messageId, long receiverId);

    int deleteMessageReceivers(long messageId, long receiverId);

}
