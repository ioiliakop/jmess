package MessagingApp.DAO;

import MessagingApp.Entities.Message;

import java.util.List;

public interface MessageDAO {

    Message getMessage(long messageId);

    List<Message> getAllMessages();

    List<Message> getAllUserMessages(long userId);

    List<Message> getConversation(long user1Id, long user2Id);

    /* date_time is assigned at the database by default during the insert operation */
    long insertMessage(String messageSubject, String messageBody, long authorId, long receiverId);

    int updateMessage(String messageSubject, String messageBody, long messageId);

    int deleteMessage(long messageId);
}
