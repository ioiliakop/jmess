package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.FinalEntities.*;
import static MessagingApp.Entities.FinalEntities.MessageContainers.TRASH;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class DeleteAllMessagesFromContainerOption extends MenuOption {

    private MessageContainers container;

    public DeleteAllMessagesFromContainerOption(User user, MessageContainers container) {
        super(user);
        this.container = container;
        this.setMenuLine("Delete all messages from " + container.name());
    }

    @Override
    public void execute() {
        User                    owner          = this.getUser();
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), container);

        if (!messageIdsList.isEmpty()) {
            if (requestConfirmation("All messages in " + container.name() + " will be moved to trash.\nAre you sure?")) {

                int numberOfUpdatedMessages = ucmDAO.updateAllUserContainerMessages(container, owner.getId(), TRASH);
                if (numberOfUpdatedMessages > 0) {
                    System.out.println(numberOfUpdatedMessages + " messages successfully moved to trash.\n" +
                            container.name() + " is now empty.");
                } else System.out.println("Unknown error. No messages were deleted.");

            }
        } else System.out.println("There are no messages to delete...");

        pauseExecution();
    }

}
