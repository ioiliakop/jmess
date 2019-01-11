package MessagingApp.Menus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.Constants.MessageContainers.SENTBOX;
import static MessagingApp.Entities.Constants.MessageContainers.TRASH;
import static MessagingApp.Menus.MenuUtils.getMessageIdInList;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.Services.getMessagesFromMessageIds;
import static MessagingApp.Menus.Services.printMessages;

public class DeleteMessageFromSentboxOption extends MenuOption {

    private static final String MENU_LINE = "Delete a message from Sentbox";
    private              User   owner;

    public DeleteMessageFromSentboxOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>                      messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), SENTBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(inboxMessages);

            long selectedMessageId = getMessageIdInList(messageIdsList);

            if (selectedMessageId != 0) {

                if (requestConfirmation("Message with id '" + selectedMessageId + "' will be moved to trash.\nAre you sure?")) {

                    if (ucmDAO.updateUserContainerMessage(owner.getId(), TRASH, selectedMessageId) == 1) {
                        System.out.println("Message successfully moved to trash");
                    }

                }
            }


        } else System.out.println("You have no messages to delete...");

        pauseExecution();
    }
}