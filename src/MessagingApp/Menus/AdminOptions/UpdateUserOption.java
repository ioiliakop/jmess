package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.UpdateUserOptions.UpdateUserOptionsMenu;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.MenuOption;
import MessagingApp.MessagingAppException;

import static MessagingApp.Entities.Statuses.Status.ACTIVE;
import static MessagingApp.Menus.Utils.inputGeneric;
import static MessagingApp.Menus.Utils.pauseExecution;

/**
 * Option that prompts the admin to enter a user to update
 * IF admin enters a valid user, ONLY THEN it calls the 'Update user submenu',
 * while passing the selected user as a parameter to the submenu
 * Otherwise it ends it's execution, which goes back to parent admin options menu
 * In other words it acts as an intermediate layer between the AdminOptionMenu and the sub menu UpdateUserOptionsMenu
 */
public class UpdateUserOption extends MenuOption {

    public UpdateUserOption() {
        super("Update an active user account");
    }

    @Override
    public void execute() throws MessagingAppException {
        String  targetUsername = inputGeneric("Which user do you want to edit/update? ");
        UserDAO usrDAO         = new MySQLUserDAO();
        User    targetUser     = usrDAO.getUser(targetUsername);

        // validate given user exists
        if (targetUser != null) {

            if (targetUser.getStatusId() == ACTIVE.ID()) {

                Menu updateUserMenu = new UpdateUserOptionsMenu(targetUser);
                updateUserMenu.execute();

            } else System.out.println("Selected user has been deleted. You should reactivate his account first.");

        } else System.out.println("User not found");
        pauseExecution();
    }
}
