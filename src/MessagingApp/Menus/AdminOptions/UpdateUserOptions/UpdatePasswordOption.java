package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.*;

public class UpdatePasswordOption extends MenuOption {

    public UpdatePasswordOption(User user) {
        super(user, "Update " + user.getUsername() + "'s password");
    }

    @Override
    public void execute() {

        String newPassword  = inputPassword();
        String passwordMD5 = getMD5Of(newPassword);

        if (requestConfirmation("Proceed with password update? ")) {

            User   updatedUser = this.getUser();
            updatedUser.setPassword(passwordMD5);
            UserDAO usrDAO = new MySQLUserDAO();

            if (usrDAO.updateUser(updatedUser) == 1) System.out.println("Password successfully updated.");
            else System.out.println("Unknown error. Password update failed.");

        } else System.out.println("Update cancelled.");

        pauseExecution();

    }

}
