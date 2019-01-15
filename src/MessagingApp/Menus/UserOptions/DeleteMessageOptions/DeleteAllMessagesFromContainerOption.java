package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.MessageFolders;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class DeleteAllMessagesFromContainerOption extends MenuOption {

    private MessageFolders.Folder container;

    public DeleteAllMessagesFromContainerOption(User user, MessageFolders.Folder container) {
        super(user);
        this.container = container;
        this.setMenuLine("Delete all messages from " + container.name());
    }

    @Override
    public void execute() {
        User                 owner          = this.getUser();
        UserFolderMessageDAO ucmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ucmDAO.getUserFolderMessages(owner.getId(), container);

        if (!messageIdsList.isEmpty()) {
            if (requestConfirmation("All messages in " + container.name() + " will be moved to TRASH.\nAre you sure?")) {

                int numberOfUpdatedMessages = ucmDAO.updateAllUserFolderMessages(container, owner.getId(), TRASH);
                if (numberOfUpdatedMessages > 0) {
                    System.out.println(numberOfUpdatedMessages + " messages successfully moved to trash.\n" +
                            container.name() + " is now empty.");
                } else System.out.println("Unknown error. No messages were deleted.");

            }
        } else System.out.println("There are no messages to delete...");

        pauseExecution();
    }

}
