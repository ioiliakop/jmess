package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Menus.Utils.pauseExecution;
import static MessagingApp.Menus.Utils.requestConfirmation;

/**
 * This user option is used to directly delete all messages from a folder
 * In this application we only use it for the TRASH folder
 * Other delete operations are moving the messages to the TRASH folder
 * But it can easily be performed on other folders if needed in the future
 * by passing the target folder as a parameter in the constructor
 */
public class EmptyFolderOption extends MenuOption {

    private Folder folder;

    public EmptyFolderOption(User user, Folder folder) {
        super(user, "Empty " + folder);
        this.folder = folder;
    }

    @Override
    public void execute() {
        UserFolderMessageDAO ufmDAO           = new MySQLUserFolderMessageDAO();
        List<Long>           messagesInFolder = ufmDAO.getUserFolderMessageIDs(this.getUser().getId(), folder);

        // First check if there are messages in folder
        if (!messagesInFolder.isEmpty()) {
            System.out.println("Attention. All messages in " + folder + " will pe permanently deleted.");
            if (requestConfirmation("Continue?")) {
                if (requestConfirmation("This action is IRREVERSIBLE. Really continue?")) {

                    int numberOfDeletedMessages = ufmDAO.deleteUserAllFolderMessages(this.getUser().getId(), folder);
                    System.out.println(numberOfDeletedMessages + " messages were successfully deleted");

                } else System.out.println("Operation cancelled. Phew, that was close");
            } else System.out.println("Operation cancelled");
        } else System.out.println("There are no messages in " + folder);
        pauseExecution();
    }

}
