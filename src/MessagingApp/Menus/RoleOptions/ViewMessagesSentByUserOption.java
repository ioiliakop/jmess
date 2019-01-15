package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.printMessages;

public class ViewMessagesSentByUserOption extends MenuOption {

    public ViewMessagesSentByUserOption() {
        super("View all messages sent by a specific user");
    }

    @Override
    public void execute() {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO       = new MySQLUserDAO();
        User    selectedUser = usrDAO.getUser(username);

        if (selectedUser != null) {
            MessageDAO    msgDAO               = new MySQLMessageDAO();
            List<Message> selectedUserMessages = msgDAO.getAllMessagesSentByUser(selectedUser.getId());

            if (!selectedUserMessages.isEmpty()) {
                printMessages(selectedUserMessages);
                if (requestConfirmation("Do you want to edit any of " + selectedUser.getUsername() + "'s sent messages?")){

                }
            }
            else System.out.println("User '" + selectedUser.getUsername() + "' hasn't sent any messages. Ever...");

        } else System.out.println("User not found");

        pauseExecution();
    }

}
