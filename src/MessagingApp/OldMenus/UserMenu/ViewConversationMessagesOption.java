package MessagingApp.OldMenus.UserMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import static MessagingApp.Menus.MenuUtils.inputUsername;
//import static MessagingApp.Menus.Services.conversationExists;
import static MessagingApp.Menus.Services.getDAOUser;

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
