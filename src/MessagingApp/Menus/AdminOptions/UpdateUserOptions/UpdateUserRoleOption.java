package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;
import MessagingApp.MessagingAppException;

import java.util.ArrayList;
import java.util.List;

import static MessagingApp.Menus.Utils.inputGeneric;
import static MessagingApp.Menus.Utils.pauseExecution;
import static MessagingApp.Menus.Utils.requestConfirmation;

/**
 * Admin option to assign/change a user's role/privileges
 */
public class UpdateUserRoleOption extends MenuOption {

    public UpdateUserRoleOption(User targetUser) {
        super(targetUser, "Update role");
    }

    @Override
    public void execute() throws MessagingAppException {

        // We print available roles
        System.out.println("Available roles are:");
        List<String> rolesIndexing = new ArrayList<>();
        for (Role role : Role.values()) {
            System.out.println(role.ID() + " - " + role);
            rolesIndexing.add(String.valueOf(role.ID()));
        }

        // We take user input for role selection and proceed accordingly
        String newRoleInput = inputGeneric("Select new role: (1-5)\n");

        if (rolesIndexing.contains(newRoleInput)) {

            if (requestConfirmation("Proceed with role update? ")) {

                // no need to try-catch exception here
                int newRoleId = Integer.parseInt(newRoleInput);
                User updatedUser = this.getUser();
                updatedUser.setRoleId(newRoleId);

                UserDAO usrDAO = new MySQLUserDAO();
                if (usrDAO.updateUser(updatedUser) == 1) System.out.println("Role successfully updated.");
                else throw new MessagingAppException("Unknown error. Update failed.");

            } else System.out.println("Update cancelled.");

        } else System.out.println("Not a valid role.");

        pauseExecution();
    }

}
