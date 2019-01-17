package MessagingApp.DAO;

import MessagingApp.Entities.MessageFolders.Folder;

import java.util.List;

/* This interface is used to assign messages to user folders and interact with folders */
public interface UserFolderMessageDAO {

    List<Long> getUserFolderMessageIDs(long userId, Folder folder);

    long insertUserFolderMessage(long userId, Folder folder, long messageId);

    int updateUserFolderMessage(long userId, Folder folder, long messageId);

    int updateAllUserFolderMessages(Folder originalFolder, long userId, Folder targetFolder);

    int deleteUserAllFolderMessages(long userId, Folder folder);

    int deleteUserFolderMessage(long userId, Folder folder, long messageId);

}
