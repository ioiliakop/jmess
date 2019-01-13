package MessagingApp.DAO;

import java.util.List;

public interface MessageReceiversDAO {

    /* Returns all receiver IDs of a given message */
    List<Long> getMessageReceivers(long messageId);

    /* Registers the receivers of the message in our db*/
    int insertMessageReceivers(long messageId, long receiverId);

    /*
     * not needed in our implementation
     * neither the message id ever gets updated, nor the receivers of a message
     */
    int updateMessageReceivers(long messageId, long receiverId);

    /* not needed in our implementation */
    int deleteMessageReceivers(long messageId, long receiverId);

}
