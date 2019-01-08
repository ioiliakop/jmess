package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLRoleDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.RoleDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.getRoleNameById;
import static MessagingApp.Menus.Services.roleExists;

public class UpdateUserRoleOption extends UpdateUserMenuOption {

    private static final String UPDATE_ROLE_MENU_LINE = "Update role";

    public UpdateUserRoleOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_ROLE_MENU_LINE);
    }

    @Override // Incomplete
    public void doAction() {

        RoleDAO rlDAO = new MySQLRoleDAO();
        System.out.println("Available roles are:");
        System.out.print(rlDAO.getAllRoles());

        String newRoleName = inputGeneric("Select new role: \n");

        if (roleExists(newRoleName)) {

            User user = this.getTargetUser();

            if (requestConfirmation("Role will be changed from " + getRoleNameById(user.getRoleId()) + " to " + newRoleName + ".")) {
                UserDAO usrDAO = new MySQLUserDAO();
                usrDAO.updateUser(user.getUsername(), user.getPassword(), user.getRoleId() , user.getId());
                System.out.println("User updated.");
            }
        } else System.out.println("Sorry, unknown role.");
        pauseExecution();
    }
}
