package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.FinalEntities.Roles;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.ArrayList;
import java.util.List;

import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class UpdateUserRoleOption extends MenuOption {

    public UpdateUserRoleOption(User user) {
        super(user, "Update user's role");
    }

    @Override
    public void execute() {

        // We print available roles
        System.out.println("Available roles are:");
        List<String> rolesIndexing = new ArrayList<>();
        for (Roles role : Roles.values()) {
            System.out.println(role.ID() + " - " + role);
            rolesIndexing.add(String.valueOf(role.ID()));
        }

        // We take user input for role selection and proceed accordingly
        String newRoleInput = inputGeneric("Select new role: (1-5)\n");

        if (rolesIndexing.contains(newRoleInput)) {

            if (requestConfirmation("Proceed with role update? ")) {

                // no need to try-catch exception here
                int newRoleId = Integer.parseInt(newRoleInput);
//            Roles newRole   = getRoleFromRoleId(newRoleId);
                User updatedUser = this.getUser();
                updatedUser.setRoleId(newRoleId);

                UserDAO usrDAO = new MySQLUserDAO();
                if (usrDAO.updateUser(updatedUser) == 1) System.out.println("Role successfully updated.");
                else System.out.println("Unknown error. Update failed.");

            } else System.out.println("Update cancelled.");

        } else System.out.println("Not a valid role.");

        pauseExecution();
    }
}
