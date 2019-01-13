package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.FinalEntities.MessageContainers.SENTBOX;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.OldMenus.Services.getMessagesFromMessageIds;
import static MessagingApp.OldMenus.Services.printMessages;

public class ViewSentboxOption extends MenuOption {

    public ViewSentboxOption(User user) {
        super(user, "View Sentbox");
    }

    @Override
    public void execute() {
        long                    ownerId        = this.getUser().getId();
        UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
        List<Long>              messageIdsList = ucmDAO.getUserContainerMessages(ownerId, SENTBOX);

        if (!messageIdsList.isEmpty()) {
            List<Message> inboxMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(inboxMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();
    }
}
