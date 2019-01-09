package MessagingApp.DAO;

import MessagingApp.Entities.Constants.MessageContainers;

import java.util.List;

public interface UserContainerMessageDAO {

    List<Long> getUserContainerMessages(long userId, MessageContainers container);

    long insertUserContainerMessage(long userId, MessageContainers container, long messageId);

    int updateUserContainerMessage(long userId, MessageContainers container, long messageId);

    int deleteUserContainerMessage(long userId, MessageContainers container, long messageId);
}
