package MessagingApp.OldMenus.UserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.FinalEntities.MessageContainers.SENTBOX;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.OldMenus.Services.getMessagesFromMessageIds;
import static MessagingApp.OldMenus.Services.printMessages;

public class ViewSentboxOption extends MenuOption {

    private static final String MENU_LINE = "View sent messages";
    private              User   owner;

    public ViewSentboxOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(owner.getId(), SENTBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }

}
