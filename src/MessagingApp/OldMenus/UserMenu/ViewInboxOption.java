package MessagingApp.OldMenus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.Services.getMessagesFromMessageIds;
import static MessagingApp.Menus.Services.printMessages;

public class ViewInboxOption extends MenuOption {

    private static final String MENU_LINE = "View Inbox";
    private              User   owner;

    public ViewInboxOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    public ViewInboxOption(User owner) {
        this.owner = owner;
    }


    @Override
    public void doAction() {
        UserFolderMessageDAO ucmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ucmDAO.getUserFolderMessages(owner.getId(), INBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
           printMessages(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }
}
