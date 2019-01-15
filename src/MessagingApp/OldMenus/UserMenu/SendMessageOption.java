package MessagingApp.OldMenus.UserMenu;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.getDAOUser;

public class SendMessageOption extends MenuOption {

    private static final String MENU_LINE = "Send a message to a user";
    private              User   sender;

    public SendMessageOption(int option, User sender) {
        super(option, MENU_LINE);
        this.sender = sender;
    }

    @Override
    public void doAction() {
        String  username = inputGeneric("Which user do you want to message?\n");
        UserDAO usrDAO   = new MySQLUserDAO();
        User    receiver = usrDAO.getUser(username);

        if (receiver != null) {
            /* We fetch the receiver user and take message subject and body from the user */
            String messageSubject = inputMessageSubject();
            String messageBody    = inputMessageBody();

            /* We then insert the message int to the messages table */
            MessageDAO msgDAO    = new MySQLMessageDAO();
//            long       messageId = msgDAO.insertMessage(messageSubject, messageBody, sender.getId(), receiver.getId());
//            Message sentMessage = msgDAO.getMessage(messageId);
//            appendMessageToFile(sentMessage);
//
//            /* And assign it to sender's sentbox and receiver's inbox */
//            UserFolderMessageDAO ucmDAO = new MySQLUserFolderMessageDAO();
//            ucmDAO.insertUserFolderMessage(sender.getId(), SENTBOX, messageId);
//            ucmDAO.insertUserFolderMessage(receiver.getId(), INBOX, messageId);

        } else System.out.println("Sorry, username not found.");

        pauseExecution();
    }
}
