package MessagingApp.DAO;

import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.User;

import java.util.List;

/* This interface is used to assign messages to user folders and interact with folders */
public interface UserFolderMessageDAO {

    List<Long> getUserFolderMessageIDs(long userId, Folder folder);

    long getUnreadMessagesCountInFolder(User user, Folder folder);

    long insertUserFolderMessage(long userId, Folder folder, long messageId);

    int updateUserFolderMessage(long userId, Folder folder, long messageId);

    long updateUserFolderMessagesAsRead(User user, Folder folder);

    /* Moves all messages send by a specific user, from their current folder to a target folder */
    long updateUserFolderMessagesSentBy(User owner, Folder originalFolder, Folder targetFolder, User sender);

    int updateAllUserFolderMessages(Folder originalFolder, long userId, Folder targetFolder);

    int deleteUserAllFolderMessages(long userId, Folder folder);

    int deleteUserFolderMessage(long userId, Folder folder, long messageId);

}
