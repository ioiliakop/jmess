package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.getMessagesFromMessageIds;
import static MessagingApp.Menus.MessageServices.messageIsValidForMoveTo;

/**
 * In this app we have to check if message should go back to inbox or sentbox
 */
public class RestoreAllMessagesFromTrashOption extends MenuOption {

    public RestoreAllMessagesFromTrashOption(User user) {
        super(user, "Restore all messages from TRASH");
    }

    @Override
    public void execute() {
        User                 trashOwner        = this.getUser();
        UserFolderMessageDAO ufmDAO            = new MySQLUserFolderMessageDAO();
        List<Long>           messageIDsInTrash = ufmDAO.getUserFolderMessageIDs(trashOwner.getId(), TRASH);

        if (!messageIDsInTrash.isEmpty()) {
            List<Message> messagesInTrash = getMessagesFromMessageIds(messageIDsInTrash);

            if (requestConfirmation("All messages will be restored. Are you sure?")) {

                for (Message m : messagesInTrash) {
                    // TODO Check if message is valid for both inbox and sentbox
                    if (messageIsValidForMoveTo(trashOwner, m, INBOX)) {
                        if (ufmDAO.updateUserFolderMessage(trashOwner.getId(), INBOX, m.getId()) == 1) {
                            System.out.println("Message successfully moved to " + INBOX);
                        } else System.out.println("Unknown error. Message move operation failed.");
                    }
                    if (messageIsValidForMoveTo(trashOwner, m, SENTBOX)) {
                        if (ufmDAO.updateUserFolderMessage(trashOwner.getId(), SENTBOX, m.getId()) == 1) {
                            System.out.println("Message successfully moved to " + SENTBOX);
                        } else System.out.println("Unknown error. Message move operation failed.");
                    }
                }

            }

        } else System.out.println("There are no messages in " + TRASH);
        pauseExecution();
    }
}

