package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.FinalEntities.MessageContainers;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.OldMenus.Services.getMessagesFromMessageIds;
import static MessagingApp.OldMenus.Services.printMessages;


/* User option that prints all user messages in container passed as parameter */
public class ViewContainerMessagesOption extends MenuOption {

    private MessageContainers container;

    public ViewContainerMessagesOption(User user, MessageContainers container) {
        super(user);
        this.container = container;
        this.setMenuLine("View messages in " + container.name());
    }

    @Override
    public void execute() {
        long                    ownerId        = this.getUser().getId();
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(ownerId, container);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }

}
