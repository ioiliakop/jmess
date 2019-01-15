package MessagingApp.OldMenus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MessageServices;
import MessagingApp.OldMenus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;
import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.MessageServices.getMessagesFromMessageIds;
import static MessagingApp.Menus.MessageServices.printMessages;


public class DeleteMessageFromInboxOption extends MenuOption {

    private static final String MENU_LINE = "Delete a message from Inbox";
    private              User   owner;

    public DeleteMessageFromInboxOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {

/*        MenuOption viewInbox = new ViewInboxOption(owner);
        viewInbox.doAction();*/

        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(owner.getId(), INBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(inboxMessages);

            long selectedMessageId = MessageServices.getMessageIdInList(messageIdsList);

            if (selectedMessageId != 0) {

                if (requestConfirmation("Message with id '" + selectedMessageId + "' will be moved to trash.\nAre you sure?")) {

                    if (ufmDAO.updateUserFolderMessage(owner.getId(), TRASH, selectedMessageId) == 1) {
                        System.out.println("Message successfully moved to trash");
                    }

                }
            }


        } else System.out.println("You have no messages to delete...");

        pauseExecution();
    }
}
