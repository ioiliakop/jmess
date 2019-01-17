package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

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

public class DeleteMessagesSentByFromFolderOption extends MenuOption {

    private Folder folder;

    public DeleteMessagesSentByFromFolderOption(User owner, Folder folder) {
        super(owner, "Delete all messages sent by a specific user from " + folder.name());
        this.folder = folder;
    }

    @Override
    public void execute() {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO       = new MySQLUserDAO();
        User    selectedUser = usrDAO.getUser(username);

        if (selectedUser != null) {
            User                 folderOwner            = this.getUser();
            UserFolderMessageDAO ufmDAO                 = new MySQLUserFolderMessageDAO();
            List<Long>           selectedUserMessageIDs = ufmDAO.getMessageIDsInFolderSentByUser(folderOwner, folder, selectedUser);
            if (!selectedUserMessageIDs.isEmpty()) {
                System.out.println("There are " + selectedUserMessageIDs.size() + " in " + folder);
                if (requestConfirmation("Are you sure you want to sent them all to TRASH?")) {
                    long rowsAffected = ufmDAO.updateUserFolderMessagesSentBy(folderOwner, folder, TRASH, selectedUser);
                    System.out.println(rowsAffected + " messages successfully moved to TRASH");
                } else System.out.println("Operation cancelled");
            } else System.out.println("There are no messages by user " + selectedUser.getUsername() + " in " + folder);

        } else System.out.println("User not found");

        pauseExecution();
    }
}
