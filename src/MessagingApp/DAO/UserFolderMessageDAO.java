package MessagingApp.DAO;

import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.User;

import java.util.List;

/** This interface is used to assign messages to user folders and interact with folders */
public interface UserFolderMessageDAO {

    List<Long> getUserFolderMessageIDs(long userId, Folder folder);

    List<Long> getMessageIDsInFolderSentByUser(User folderOwner, Folder folder, User sender);

    long getUnreadMessagesCountInFolder(User folderOwner, Folder folder);

    long insertUserFolderMessage(long userId, Folder folder, long messageId);

    int updateUserFolderMessage(long userId, Folder folder, long messageId);

    long updateUserFolderMessagesAsRead(User folderOwner, Folder folder);

    /* Moves all messages sent by a specific user, from their current folder to a target folder */
    long updateUserFolderMessagesSentBy(User folderOwner, Folder originalFolder, Folder targetFolder, User sender);

    int updateAllUserFolderMessages(Folder originalFolder, long userId, Folder targetFolder);

    int deleteUserAllFolderMessages(long userId, Folder folder);

    int deleteUserFolderMessage(long userId, Folder folder, long messageId);

}
