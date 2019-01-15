package MessagingApp.DAO;

import MessagingApp.Entities.MessageFolders.Folder;

import java.util.List;

public interface UserContainerMessageDAO {

    List<Long> getUserContainerMessages(long userId, Folder container);

    long insertUserContainerMessage(long userId, Folder container, long messageId);

    int updateUserContainerMessage(long userId, Folder container, long messageId);

    int updateAllUserContainerMessages(Folder originalContainer, long userId, Folder targetContainer);

    int deleteUserAllContainerMessages(long userId, Folder container);

    int deleteUserContainerMessage(long userId, Folder container, long messageId);

}
