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
 * In this app we have to check if message should go back to INBOX or SENTBOX
 * E.g. a message the user had previously received, shouldn't go back to SENTBOX, but to INBOX
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

        // First check if TRASH folder has any messages at all
        if (!messageIDsInTrash.isEmpty()) {
            List<Message> messagesInTrash = getMessagesFromMessageIds(messageIDsInTrash);

            if (requestConfirmation("All messages will be restored. Are you sure?")) {

                for (Message m : messagesInTrash) {
                    // Check for case where user is both sender and receiver of a message
                    if (messageIsValidForMoveTo(trashOwner, m, INBOX) && messageIsValidForMoveTo(trashOwner, m, SENTBOX)) {
                        System.out.println("Message with id " + m.getId() + " can be restored to INBOX and SENTBOX");
                        // Asks the user where to restore the message and proceeds accordingly
                        if (requestConfirmation("Would you like to restore it to INBOX?")) {
                            long rowsMoved = ufmDAO.updateUserFolderMessage(trashOwner.getId(), INBOX, m.getId());
                            if (rowsMoved == 1 || rowsMoved == 2) {
                                System.out.println("Message with id " + m.getId() + " successfully moved to " + INBOX);
                            } else System.out.println("Unknown error. Message move operation failed.");
                        } else {
                            long rowsMoved = ufmDAO.updateUserFolderMessage(trashOwner.getId(), SENTBOX, m.getId());
                            if (rowsMoved == 1 || rowsMoved == 2) {
                                System.out.println("Message with id " + m.getId() + " successfully moved to " + SENTBOX);
                            } else System.out.println("Unknown error. Message move operation failed.");
                        }
                        // Case where the user is only a receiver of the message
                    } else if (messageIsValidForMoveTo(trashOwner, m, INBOX)) {
                        if (ufmDAO.updateUserFolderMessage(trashOwner.getId(), INBOX, m.getId()) == 1) {
                            System.out.println("Message with id " + m.getId() + " successfully moved to " + INBOX);
                        } else System.out.println("Unknown error. Message move operation failed.");
                        // Case where the user is only sender of the message
                    } else if (messageIsValidForMoveTo(trashOwner, m, SENTBOX)) {
                        if (ufmDAO.updateUserFolderMessage(trashOwner.getId(), SENTBOX, m.getId()) == 1) {
                            System.out.println("Message with id " + m.getId() + " successfully moved to " + SENTBOX);
                        } else System.out.println("Unknown error. Message move operation failed.");
                    }
                }

            }

        } else System.out.println("There are no messages in " + TRASH);
        pauseExecution();
    }
}

