package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.Services.getMessageIdInList;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.Services.getMessagesFromMessageIds;
import static MessagingApp.Menus.Services.printMessages;

public class DeleteSpecificMessagesFromContainerOption extends MenuOption {

    private Folder container;

    public DeleteSpecificMessagesFromContainerOption(User user, Folder container) {
        super(user);
        this.container = container;
        this.setMenuLine("Delete select messages from " + container.name());
    }

    @Override
    public void execute() {

        User                    owner  = this.getUser();
        UserContainerMessageDAO ucmDAO = new MySQLUserContainerMessageDAO();

        List<Long> messageIdsList;
        do {
            messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), container);

            if (!messageIdsList.isEmpty()) {
                // First we print the available messages list to help the user choose
                List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
                printMessages(inboxMessages);

                // We get user selection
                long selectedMessageId = getMessageIdInList(messageIdsList);

                // We validate it and proceed accordingly
                if (selectedMessageId != 0) {

                    if (requestConfirmation("Message with id '" + selectedMessageId + "' will be moved to trash.\nAre you sure?")) {
                        if (ucmDAO.updateUserContainerMessage(owner.getId(), TRASH, selectedMessageId) == 1) {
                            System.out.println("Message successfully moved to trash");
                        } else System.out.println("Unknown error. Message delete operation failed.");
                    } else System.out.println("Delete operation cancelled.");
                } // no need to print related message here. It's already printed by getMessageIdInList

            } else {
                System.out.println("There are no messages in " + container + " to delete...");
                break;
            }
            // TODO adjust exit loop condition. It asks to repeat even if no messages. Added break temporarily
        } while (requestConfirmation("Would you like to delete another message?"));

        pauseExecution();
    }

}
