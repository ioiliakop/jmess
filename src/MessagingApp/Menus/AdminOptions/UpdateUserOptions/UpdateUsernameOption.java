package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.FinalEntities;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Entities.FinalEntities.getUserRoleFromRoleId;
import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class UpdateUsernameOption extends MenuOption {

    public UpdateUsernameOption(User selectedUser) {
        super(selectedUser, "Update " + selectedUser.getUsername() + "'s username");
    }

    @Override
    public void execute() {
        String  newUsername = inputUsername();
        UserDAO usrDAO      = new MySQLUserDAO();
        User    user        = usrDAO.getUser(newUsername);

        if (user == null) {

            if (requestConfirmation("Username will be changed from " + this.getUser().getUsername() + " to " + newUsername + ".")) {
                usrDAO.updateUserNameRole(newUsername, selectedUser.getId());
                System.out.println("Username updated to: " + newUsername);
            }
        } else System.out.println("Sorry, username not available.");
        pauseExecution();

    }
}
