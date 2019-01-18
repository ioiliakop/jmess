package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MessageServices.*;


/**
 * User option that prints all user messages in the folder (INBOX/SENTBOX etc.) passed as parameter
 */
public class ViewFolderMessagesOption extends MenuOption {

    private Folder folder;

    public ViewFolderMessagesOption(User folderOwner, Folder folder) {
        super(folderOwner);
        this.folder = folder;
        this.setMenuLine("View messages in " + folder.name());
    }

    @Override
    public void execute() {
        User                 folderOwner    = this.getUser();
        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(folderOwner.getId(), folder);

        // First check if there are messages in the folder in the first place
        if (!messageIdsList.isEmpty()) {
            List<Message> folderMessages = getMessagesFromMessageIds(messageIdsList);
            System.out.println("\nMessages in " + ANSI_WHITE + folder + ANSI_RESET + ":");
            printMessages(folderMessages);

            // We then mark all messages as read
            ufmDAO.updateUserFolderMessagesAsRead(folderOwner, folder);

        } else System.out.println("No messages in " + folder);

        pauseExecution();
    }

}
