package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.Roles.Role.DELETER;
import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Entities.Roles.getRoleFromRoleId;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.printMessages;
import static MessagingApp.Menus.RoleOptions.RoleHelper.*;

/* Option to View/Edit/Delete messages sent by a user depending on role */
public class RoleMessagesSentByUserOption extends MenuOption {

    public RoleMessagesSentByUserOption(User roleUser) {
        super(roleUser, getRoleAbilitiesString(getRoleFromRoleId(roleUser.getRoleId())) + "all messages sent by a specific user");
    }

    @Override
    public void execute() {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO       = new MySQLUserDAO();
        User    selectedUser = usrDAO.getUser(username);

        if (selectedUser != null) {
            MessageDAO    msgDAO               = new MySQLMessageDAO();
            List<Message> selectedUserMessages = msgDAO.getMessagesSentByUser(selectedUser);

            viewEditDeleteMessagesInList(this.getUser(), selectedUserMessages);

        } else System.out.println("User not found");

        pauseExecution();
    }

}


