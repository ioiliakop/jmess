package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserContainerMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserContainerMessageDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.ArrayList;
import java.util.List;

import static MessagingApp.Entities.FinalEntities.MessageContainers.*;
import static MessagingApp.Entities.FinalEntities.Status.ACTIVE;
import static MessagingApp.Menus.MenuUtils.*;

public class SendMessageOption extends MenuOption {

    public SendMessageOption(User user) {
        super(user, "Send a message");
    }

    @Override
    public void execute() {

        // In the first section we ask and get from the user, a list of users he wants to message
        List<User> messageReceivers = new ArrayList<>();
        do {
            String  username = inputGeneric("Which user do you want to message?\n");
            UserDAO usrDAO   = new MySQLUserDAO();
            User    receiver = usrDAO.getUser(username);
            if (receiver != null) {

                if (receiver.getStatusId() == ACTIVE.ID()) {

                    if (messageReceivers.contains(receiver)) System.out.println("User is already in recipients");
                    else messageReceivers.add(receiver);

                } else System.out.println("This user has been deleted. You can no longer contact him.");

            } else System.out.println("User not found.");

        } while (requestConfirmation("Would you like to add another receiver? "));

        // If the list contains one or more receivers we proceed sending the message
        if (!messageReceivers.isEmpty()) {
            // We take message subject and body from the user
            String     messageSubject = inputMessageSubject();
            String     messageBody    = inputMessageBody();
            User       sender         = this.getUser();
            MessageDAO msgDAO         = new MySQLMessageDAO();

            // Then we insert the message into the messages table
            long messageId = msgDAO.insertMessage(messageSubject, messageBody, sender.getId());
            if (messageId != 0) {
                // If sent successfully we register it in the user's sentbox
                UserContainerMessageDAO ucmDAO = new MySQLUserContainerMessageDAO();
                ucmDAO.insertUserContainerMessage(sender.getId(), SENTBOX, messageId);

                // Then for each receiver
                MessageReceiversDAO mrDAO = new MySQLMessageReceiversDAO();
                for (User receiver : messageReceivers) {
                    //We insert each receiver id along with the message id, in the messages_receivers table
                    mrDAO.insertMessageReceivers(messageId, receiver.getId());
                    // And register in each receiver's inbox
                    ucmDAO.insertUserContainerMessage(receiver.getId(), INBOX, messageId);
                }
            } else System.out.println("Unknown error. Message was not sent.");


        } else System.out.println("No valid receivers selected.");

        pauseExecution();
    }

}
