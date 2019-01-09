package MessagingApp.Menus.UserMenu;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.inputUsername;
//import static MessagingApp.Menus.Services.conversationExists;
import static MessagingApp.Menus.Services.getDAOUser;
import static MessagingApp.Menus.Services.printMessages;

public class ViewConversationMessagesOption extends MenuOption {

    private static final String MENU_LINE = "View conversation with a specific user";

    private User owner;

    public ViewConversationMessagesOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {
/*        String username = inputUsername("Your conversation with which user?\n");
        if (conversationExists(owner,getDAOUser(username))){
            MessageDAO    msgDAO = new MySQLMessageDAO();
            List<Message> conversation = msgDAO.getConversation(owner.ID(),getDAOUser(username).ID());
            printMessages(conversation);
        } else System.out.println("Sorry, conversation doesn't exist");*/
    }
}
