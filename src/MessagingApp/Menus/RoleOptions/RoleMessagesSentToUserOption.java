package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;
import MessagingApp.MessagingAppException;

import java.util.List;

import static MessagingApp.Entities.Roles.getRoleFromRoleId;
import static MessagingApp.Menus.Utils.inputGeneric;
import static MessagingApp.Menus.Utils.pauseExecution;
import static MessagingApp.Menus.RoleOptions.RoleHelper.*;

/**
 * Option to View/Edit/Delete messages sent to a user depending on role
 * Depending on role, view, view/edit or all 3 view/edit/delete options are available
 */
public class RoleMessagesSentToUserOption extends MenuOption {

    public RoleMessagesSentToUserOption(User roleUser) {
        super(roleUser, getRoleAbilitiesString(getRoleFromRoleId(roleUser.getRoleId())) + "all messages sent to a specific user");
    }

    @Override
    public void execute() throws MessagingAppException {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO       = new MySQLUserDAO();
        User    selectedUser = usrDAO.getUser(username);

        // Validate selected user
        if (selectedUser != null) {
            MessageDAO    msgDAO               = new MySQLMessageDAO();
            List<Message> selectedUserMessages = msgDAO.getMessagesSentToUser(selectedUser);
            User          roleUser             = this.getUser();
            viewEditDeleteMessagesInList(roleUser, selectedUserMessages);

        } else System.out.println("User not found");

        pauseExecution();
    }

}
