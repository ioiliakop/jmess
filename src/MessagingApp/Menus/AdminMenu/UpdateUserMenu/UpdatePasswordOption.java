package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;

import static MessagingApp.Menus.MenuUtils.*;


public class UpdatePasswordOption extends UpdateUserMenuOption {

    private static final String UPDATE_PASSWORD_MENU_LINE = "Update password";

    public UpdatePasswordOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_PASSWORD_MENU_LINE);
    }

    @Override // Incomplete
    public void doAction() {

        String newPassword  = inputGeneric("Enter new password: ");
        User   selectedUser = this.getTargetUser();

        if (requestConfirmation("Proceed with password update? ")) {
            UserDAO usrDAO = new MySQLUserDAO();

            if (usrDAO.updateUserPassword(newPassword, selectedUser.getId()) == 1) {
                System.out.println("Password successfully updated.");
            } else System.out.println("Unknown error. Password update failed.");

        }
        pauseExecution();
    }

}

