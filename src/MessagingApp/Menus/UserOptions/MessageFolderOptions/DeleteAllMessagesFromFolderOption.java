package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

/**
 * This Option gives the ability to delete messages from specific user's folder (INBOX, SENTBOX, TRASH etc.)
 * The messages and their respective content will still remain in the db
 * Available for inspection and handling by higher role users
 */
public class DeleteAllMessagesFromFolderOption extends MenuOption {

    private Folder folder;

    public DeleteAllMessagesFromFolderOption(User user, Folder folder) {
        super(user);
        this.folder = folder;
        this.setMenuLine("Delete all messages from " + folder.name());
    }

    @Override
    public void execute() {

        String  senderName = inputGeneric("Which user's messages do you want to delete from " + folder.name() + " ?");
        UserDAO usrDAO     = new MySQLUserDAO();
        User    sender     = usrDAO.getUser(senderName);

        if (sender != null) {
            User                 owner          = this.getUser();
            UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
            List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(owner.getId(), folder);

            if (!messageIdsList.isEmpty()) {
                if (requestConfirmation("All messages in " + folder.name() + " will be moved to TRASH.\nAre you sure?")) {

                    int numberOfUpdatedMessages = ufmDAO.updateAllUserFolderMessages(folder, owner.getId(), TRASH);
                    if (numberOfUpdatedMessages > 0) {
                        System.out.println(numberOfUpdatedMessages + " messages successfully moved to trash.\n" +
                                folder.name() + " is now empty.");
                    } else System.out.println("Unknown error. No messages were deleted.");

                }
            } else System.out.println("There are no messages to delete...");

        } else System.out.println("User not found");
        pauseExecution();
    }

}
