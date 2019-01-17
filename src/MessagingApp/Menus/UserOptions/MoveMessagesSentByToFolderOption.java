package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

/**
 * This is a flexible option
 * Can move all messages sent by a specific user from their current folder to an other folder
 * e.g. can be used for moving messages from inbox to trash, or vice versa
 * or to a different folder that could be added in the future
 */
public class MoveMessagesSentByToFolderOption extends MenuOption {

    private Folder currentFolder;
    private Folder targetFolder;

    public MoveMessagesSentByToFolderOption(User folderOwner, Folder currentFolder, Folder targetFolder, String menuLine) {
        super(folderOwner, menuLine);
        this.currentFolder = currentFolder;
        this.targetFolder = targetFolder;
    }

    @Override
    public void execute() {
        User                 folderOwner    = this.getUser();
        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(folderOwner.getId(), currentFolder);

        // First we check if there are any messages in folder
        if (!messageIdsList.isEmpty()) {

            System.out.println("Which specific user?");
            String username = inputGeneric("Enter username: ");

            UserDAO usrDAO = new MySQLUserDAO();
            User    sender = usrDAO.getUser(username);

            // If sender is a valid user
            if (sender != null) {
                List<Long> selectedUserMessageIDs = ufmDAO.getMessageIDsInFolderSentByUser(folderOwner, currentFolder, sender);

                // if there are messages by sender in folder
                if (!selectedUserMessageIDs.isEmpty()) {
                    System.out.println("There are " + selectedUserMessageIDs.size() + " messages from " + sender.getUsername() + " in " + currentFolder);
                    if (requestConfirmation("Are you sure you want to move them all to " + targetFolder + " ?")) {
                        long rowsAffected = ufmDAO.updateUserFolderMessagesSentBy(folderOwner, currentFolder, targetFolder, sender);
                        System.out.println(rowsAffected + " messages successfully moved to " + targetFolder);
                    } else System.out.println("Operation cancelled");
                } else
                    System.out.println("There are no messages by user " + sender.getUsername() + " in " + currentFolder);

            } else System.out.println("User not found");

        } else System.out.println(currentFolder + " is empty...");

        pauseExecution();
    }
}
