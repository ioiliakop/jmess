package MessagingApp.DAO;

import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;

import java.util.List;

public interface MessageDAO {

    Message getMessage(long messageId);

    List<Message> getAllMessages();

    List<Message> getMessagesSentByUser(User sender);

    List<Message> getMessagesSentToUser(User receiver);

    List<Message> getMessagesOfUser(User senderOrReceiver);

    List<Message> getConversation(long user1Id, long user2Id);

    /* date_created is assigned at the database by default during the insert operation */
    long insertMessage(String messageSubject, String messageBody, long senderId);

    int updateMessage(Message message);

    int deleteMessage(Message message);
}
