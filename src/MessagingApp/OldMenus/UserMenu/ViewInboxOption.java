package MessagingApp.OldMenus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.FinalEntities.MessageContainers.INBOX;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.OldMenus.Services.getMessagesFromMessageIds;
import static MessagingApp.OldMenus.Services.printMessages;

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
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), INBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
           printMessages(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }
}