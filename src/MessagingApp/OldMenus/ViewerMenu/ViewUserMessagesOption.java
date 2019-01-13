package MessagingApp.OldMenus.ViewerMenu;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;
import java.util.List;

import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.Services.printMessages;

public class ViewUserMessagesOption extends MenuOption {

    private static final String MENU_LINE = "View a user's messages";

    public ViewUserMessagesOption(int option) {
        super(option, MENU_LINE);
    }

    @Override
    public void doAction() {
        String username = inputUsername("Which user's messages would you like to view? \n");
        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUser(username);

        if (user != null) {
            MessageDAO msgDAO = new MySQLMessageDAO();
            List<Message> userMessages = msgDAO.getAllUserMessages(user.getId());

            if (!userMessages.isEmpty()) printMessages(userMessages);
            else System.out.println("User has no messages, either sent or received.");

        } else {
            System.out.println("Sorry, username not found.");
        }

        pauseExecution();
    }
}
