package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;


/*
 * This user option can be used to directly delete all messages from a folder
 * In this application we only use it for the TRASH folder
 * As all other delete operations are implicitly moving the messages to the TRASH folder
 * But it can easily be performed on other folders if needed in the future
 * just by passing the target folder as a parameter in the constructor
 */
public class EmptyFolderOption extends MenuOption {

    private Folder folder;

    public EmptyFolderOption(User user, Folder folder) {
        super(user, "Empty " + folder);
        this.folder = folder;
    }

    @Override
    public void execute() {
        UserFolderMessageDAO ufmDAO = new MySQLUserFolderMessageDAO();
        List<Long> messagesInFolder = ufmDAO.getUserFolderMessageIDs(this.getUser().getId(),folder);

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