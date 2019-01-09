package MessagingApp.Menus.UserMenu;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Entities.Constants.MessageContainers.INBOX;
import static MessagingApp.Entities.Constants.MessageContainers.SENTBOX;
import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.getDAOUser;
import static MessagingApp.Menus.Services.usernameExists;

public class SendMessageOption extends MenuOption {

    private static final String MENU_LINE = "Send a message to a user";
    private              User   sender;

    public SendMessageOption(int option, User sender) {
        super(option, MENU_LINE);
        this.sender = sender;
    }

    @Override
    public void doAction() {
        String username = inputUsername("Which user do you want to message?\n");

        if (usernameExists(username)) {
            /* We fetch the receiver user and take message subject and body from the user */
            User                    receiver       = getDAOUser(username);
            String                  messageSubject = inputGeneric("Enter subject: \n");
            String                  messageBody    = inputGeneric("Enter message: \n");

            /* We then insert the message int to the messages table */
            MessageDAO              msgDAO         = new MySQLMessageDAO();
            long                    messageId      = msgDAO.insertMessage(messageSubject, messageBody, sender.getId());

            /* And assign it to sender's sentbox and receiver's inbox */
            UserContainerMessageDAO ucmDAO         = new MySQLUserContainerMessageDAO();
            ucmDAO.insertUserContainerMessage(sender.getId(), SENTBOX, messageId);
            ucmDAO.insertUserContainerMessage(receiver.getId(), INBOX, messageId);

        } else System.out.println("Sorry, username not found.");

        pauseExecution();
    }
}
