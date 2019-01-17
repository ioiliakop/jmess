package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class DeleteMessagesSentByFromFolderOption extends MenuOption {

    private Folder folder;

    public DeleteMessagesSentByFromFolderOption(User owner, Folder folder) {
        super(owner, "Delete all messages sent by a specific user from " + folder.name());
        this.folder = folder;
    }

    @Override
    public void execute() {
//        User                 owner          = this.getUser();
//        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
//        List<Long>           messageIdsList = ufmDAO.getMessageIDsInFolderSentByUser(owner, folder);
//
//        if (!messageIdsList.isEmpty()) {
//            if (requestConfirmation("All messages in " + folder.name() + " will be moved to TRASH.\nAre you sure?")) {
//
//                int numberOfUpdatedMessages = ufmDAO.updateAllUserFolderMessages(folder, owner.getId(), TRASH);
//                if (numberOfUpdatedMessages > 0) {
//                    System.out.println(numberOfUpdatedMessages + " messages successfully moved to trash.\n" +
//                            folder.name() + " is now empty.");
//                } else System.out.println("Unknown error. No messages were deleted.");
//
//            }
//        } else System.out.println("There are no messages to delete...");

        pauseExecution();
    }
}
