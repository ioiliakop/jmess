package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;

import static MessagingApp.Entities.Constants.getUserRoleFromRoleId;
import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.Services.usernameExists;

public class UpdateUsernameOption extends UpdateUserMenuOption {

    private static final String UPDATE_USERNAME_MENU_LINE = "Update username";

    public UpdateUsernameOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_USERNAME_MENU_LINE);
    }

    @Override
    public void doAction() {

        String newUsername = inputUsername();
        if (!usernameExists(newUsername)) {
            User selectedUser = this.getTargetUser();

            if (requestConfirmation("Username will be changed from " + selectedUser.getUsername() + " to " + newUsername + ".")) {
                UserDAO usrDAO = new MySQLUserDAO();
                usrDAO.updateUser(newUsername, selectedUser.getPassword(), getUserRoleFromRoleId(selectedUser.getRoleId()), selectedUser.getId());
                System.out.println("USER updated.");
            }
        } else System.out.println("Sorry, username not available.");
        pauseExecution();
    }

}
