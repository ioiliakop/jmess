package MessagingApp.OldMenus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.User;

import static MessagingApp.Entities.Roles.getRoleFromRoleId;
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
                UserDAO usrDAO           = new MySQLUserDAO();
                Role    selectedUserRole = getRoleFromRoleId(selectedUser.getRoleId());
                usrDAO.updateUserNameRole(newUsername, selectedUserRole, selectedUser.getId());
                System.out.println("Username updated to: " + newUsername);
            }
        } else System.out.println("Sorry, username not available.");
        pauseExecution();
    }

}
