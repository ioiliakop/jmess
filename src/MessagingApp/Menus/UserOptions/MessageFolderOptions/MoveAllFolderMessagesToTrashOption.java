package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.*;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.Utils.pauseExecution;
import static MessagingApp.Menus.Utils.requestConfirmation;

/**
 * This Option gives the ability to delete messages from specific user's currentFolder (INBOX, SENTBOX, TRASH etc.)
 * The messages and their respective content will still remain in the db
 * Available for inspection and handling by higher role users
 */
public class MoveAllFolderMessagesToTrashOption extends MenuOption {

    private Folder currentFolder;

    public MoveAllFolderMessagesToTrashOption(User folderOwner, Folder currentFolder) {
        super(folderOwner);
        this.currentFolder = currentFolder;
        this.setMenuLine("Move all messages from " + currentFolder.name() + " to TRASH");
    }

    @Override
    public void execute() {
        // First we try to get messageIDs in folder
        User                 folderOwner    = this.getUser();
        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(folderOwner.getId(), currentFolder);

        // And check if there are any before proceeding
        if (!messageIdsList.isEmpty()) {
            if (requestConfirmation("All messages in " + currentFolder.name() + " will be moved to TRASH.\nAre you sure?")) {

                int numberOfUpdatedMessages = ufmDAO.updateAllUserFolderMessages(currentFolder, folderOwner.getId(), TRASH);
                if (numberOfUpdatedMessages > 0) {
                    System.out.println(numberOfUpdatedMessages + " messages successfully moved to trash.\n" +
                            currentFolder.name() + " is now empty.");
                } else System.out.println("Unknown error. No messages were deleted.");

            }
        } else System.out.println("There are no messages to delete...");


        pauseExecution();
    }

}
