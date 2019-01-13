package MessagingApp.DAO;

import MessagingApp.Entities.FinalEntities.MessageContainers;

import java.util.List;

public interface UserContainerMessageDAO {

    List<Long> getUserContainerMessages(long userId, MessageContainers container);

    long insertUserContainerMessage(long userId, MessageContainers container, long messageId);

    int updateUserContainerMessage(long userId, MessageContainers container, long messageId);

    int updateAllUserContainerMessages(MessageContainers originalContainer, long userId, MessageContainers targetContainer);

    int deleteUserAllContainerMessages(long userId, MessageContainers container);

    int deleteUserContainerMessage(long userId, MessageContainers container, long messageId);

}
