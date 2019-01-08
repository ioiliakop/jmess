package MessagingApp.Menus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.Constants.INBOX_ID;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.Services.getMessagesFromMessageIds;

public class ViewInboxOption extends MenuOption {

    private static final String MENU_LINE = "View Inbox";
    private              User   owner;

    public ViewInboxOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), INBOX_ID);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            System.out.println(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }
}
