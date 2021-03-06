package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;
import MessagingApp.MessagingAppException;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Menus.Utils.pauseExecution;
import static MessagingApp.Menus.Utils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.*;

public class MoveSpecificMessageFromToFolderOption extends MenuOption {

    private Folder currentFolder;
    private Folder targetFolder;

    public MoveSpecificMessageFromToFolderOption(User folderOwner, Folder currentFolder, Folder targetFolder) {
        super(folderOwner, "Move a message from " + currentFolder + " to " + targetFolder);
        this.currentFolder = currentFolder;
        this.targetFolder = targetFolder;
    }

    @Override
    public void execute() throws MessagingAppException {
        User                 folderOwner = this.getUser();
        UserFolderMessageDAO ufmDAO      = new MySQLUserFolderMessageDAO();

        List<Long> messageIdsList;
        do {
            messageIdsList = ufmDAO.getUserFolderMessageIDs(folderOwner.getId(), currentFolder);

            if (!messageIdsList.isEmpty()) {
                // First we print the available messages list to help the user choose
                List<Message> currentFolderMessages = getMessagesFromMessageIds(messageIdsList);
                System.out.println("\nMessages in " + ANSI_WHITE + currentFolder + ANSI_RESET + ":");
                printMessages(currentFolderMessages);

                // We get user selection and validate it
                long selectedMessageId = getMessageIdInList(messageIdsList);

                // We then proceed accordingly
                if (selectedMessageId != 0) {
                    MessageDAO msgDAO          = new MySQLMessageDAO();
                    Message    selectedMessage = msgDAO.getMessage(selectedMessageId);

                    if (messageIsValidForMoveTo(folderOwner, selectedMessage, targetFolder)) {

                        if (requestConfirmation("Message with id '" + selectedMessageId + "' will be moved to " + targetFolder + ".\nAre you sure?")) {
                            if (ufmDAO.updateUserFolderMessage(folderOwner.getId(), targetFolder, selectedMessageId) == 1) {
                                System.out.println("Message successfully moved to " + targetFolder);
                            } else throw new MessagingAppException("Unknown error. Message move operation failed.");
                        } else System.out.println("Move operation cancelled.");
                    } else System.out.println("Selected message cannot be moved to " + targetFolder);

                } // no need to print related message here. It's already printed by getMessageIdInList
            } else {
                System.out.println("There are no messages in " + currentFolder);
                break;
            }

        } while (requestConfirmation("Would you like to choose another message?"));

        pauseExecution();
    }
}
