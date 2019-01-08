package MessagingApp.DAO;

import MessagingApp.Entities.Message;

import java.util.List;

public interface UserContainerMessageDAO {

    List<Long> getUserContainerMessages(long userId, long containerId);

    long insertUserContainerMessage(long userId, long containerId, long messageId);

    int updateUserContainerMessage(long userId, long containerId, long messageId);

    int deleteUserContainerMessage(long userId, long containerId, long messageId);
}
