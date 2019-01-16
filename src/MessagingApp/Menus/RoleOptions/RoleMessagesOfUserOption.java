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
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.printMessages;
import static MessagingApp.Menus.RoleOptions.RoleHelper.deleteMessageInList;
import static MessagingApp.Menus.RoleOptions.RoleHelper.editMessageInList;

public class RoleMessagesOfUserOption extends MenuOption {

    public RoleMessagesOfUserOption(User roleUser) {
        super(roleUser, "View all messages of a specific user (sent or received)");
    }

    @Override
    public void execute() {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO       = new MySQLUserDAO();
        User    selectedUser = usrDAO.getUser(username);

        if (selectedUser != null) {
            MessageDAO    msgDAO               = new MySQLMessageDAO();
            List<Message> selectedUserMessages = msgDAO.getMessagesOfUser(selectedUser);

            if (!selectedUserMessages.isEmpty()) {
                printMessages(selectedUserMessages);

                if (this.getUser().getRoleId()>=EDITOR.ID()) {
                    if (requestConfirmation("Do you want to edit any of the above messages?")) {
                        editMessageInList(selectedUserMessages);
                    } else if (this.getUser().getRoleId()>=DELETER.ID()) {
                        if (requestConfirmation("Do you want to delete any of the above messages?")) {
                            deleteMessageInList(selectedUserMessages);
                        }
                    }
                }

//                if (this.getUser().getRoleId()>=DELETER.ID()) {
//                    if (requestConfirmation("Do you want to delete any of the above messages?")) {
//                        deleteMessageInList(selectedUserMessages);
//                    }
//                }

            } else System.out.println("User '" + selectedUser.getUsername() + "' hasn't received any messages. Ever...");

        } else System.out.println("User not found");

        pauseExecution();
    }


}
