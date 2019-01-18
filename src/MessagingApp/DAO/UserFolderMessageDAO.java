package MessagingApp.DAO;

import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.User;

import java.util.List;

/**
 * This interface is used to assign messages to user folders and interact with folders
 */
public interface UserFolderMessageDAO {

    List<Long> getUserFolderMessageIDs(long userId, Folder folder);

    List<Long> getMessageIDsInFolderSentByUser(User folderOwner, Folder folder, User sender);

    /* returns the number of unread messages in user's folder */
    long getUnreadMessagesCountInFolder(User folderOwner, Folder folder);

    long insertUserFolderMessage(long userId, Folder folder, long messageId);

    /* updates the folder of a specific message */
    int updateUserFolderMessage(long userId, Folder folder, long messageId);

    /* marks messages in folder as read */
    long updateUserFolderMessagesAsRead(User folderOwner, Folder folder);

    /* Moves all messages sent by a specific user, from their current folder to a target folder */
    long updateUserFolderMessagesSentBy(User folderOwner, Folder originalFolder, Folder targetFolder, User sender);

    int updateAllUserFolderMessages(Folder originalFolder, long userId, Folder targetFolder);

    /* Deletes all messages from usr folder */
    int deleteUserAllFolderMessages(long userId, Folder folder);

    /* for reference, not implemented */
    int deleteUserFolderMessage(long userId, Folder folder, long messageId);

}
